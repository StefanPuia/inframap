package uk.inframap.data.queries;

import jakarta.inject.Singleton;
import java.util.List;
import java.util.UUID;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import uk.inframap.model.infra.InfrastructureNode;
import uk.inframap.model.infra.InfrastructureNodePath;

import static org.neo4j.driver.Values.parameters;

@Singleton
public class InfrastructureRepository extends AbstractRepository {
  public InfrastructureRepository(final Driver driver) {
    super(driver);
  }

  public List<Record> findAll() {
    return read(tx -> tx.run("MATCH path=(n:Node)-[*0..]-(m:Node) RETURN distinct n, path").list());
  }

  public List<Record> findByProperty(final String property, final String value) {
    return read(
        tx ->
            tx.run(
                    """
                MATCH path = (n:Node)-[*0..]-(m:Node)
                  WHERE $value IN [n[$propName], m[$propName]]
                RETURN DISTINCT n, path""",
                    parameters("propName", property, "value", value))
                .list());
  }

  public void createNode(final InfrastructureNode node) {
    write(
        tx ->
            tx.run("CREATE (n:Node $properties) RETURN n", parameters("properties", node.toProps()))
                .list());
  }

  public void deleteNode(final UUID nodeId) {
    write(
        tx -> {
          tx.run(
              """
                  MATCH (n:Node)-[r]-(:Node)
                          WHERE n.uuid = $uuid
                          DELETE r""",
              parameters("uuid", nodeId.toString()));
          return tx.run(
                  """
                      MATCH (n:Node)
                              WHERE n.uuid = $uuid
                              DELETE n""",
                  parameters("uuid", nodeId.toString()))
              .list();
        });
  }

  public void createPath(final InfrastructureNodePath path) {
    write(
        tx ->
            tx.run(
                    """
                MATCH
                  (a:Node),
                  (b:Node)
                  WHERE a.uuid = $fromUuid AND b.uuid = $toUuid
                CREATE (a)-[r:CONNECTS]->(b)
                RETURN type(r)""",
                    parameters("fromUuid", path.from().toString(), "toUuid", path.to().toString()))
                .list());
  }

  public void deletePath(final InfrastructureNodePath path) {
    write(
        tx ->
            tx.run(
                    """
                MATCH
                  (a:Node)-[r]->(b:Node)
                  WHERE a.uuid = $fromUuid AND b.uuid = $toUuid
                DELETE r""",
                    parameters("fromUuid", path.from().toString(), "toUuid", path.to().toString()))
                .list());
  }
}
