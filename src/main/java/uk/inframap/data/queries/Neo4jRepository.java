package uk.inframap.data.queries;

import jakarta.inject.Singleton;
import java.util.List;
import java.util.UUID;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Query;
import org.neo4j.driver.Record;
import org.neo4j.driver.TransactionWork;
import uk.inframap.model.InfrastructureNode;
import uk.inframap.model.InfrastructureNodePath;

import static org.neo4j.driver.Values.parameters;

@Singleton
public class Neo4jRepository {

  private final Driver driver;

  public Neo4jRepository(final Driver driver) {
    this.driver = driver;
  }

  public List<Record> findAll() {
    return read(
        tx -> {
          var result = tx.run("MATCH path=(n)-[*0..]-(m) RETURN distinct n, path");
          return result.list();
        });
  }

  public List<Record> findByProperty(final String property, final String value) {
    return read(
        tx -> {
          var query =
              new Query(
                  """
                      MATCH path = (n)-[*0..]-(m)
                        WHERE $value IN [n[$propName], m[$propName]]
                      RETURN DISTINCT n, path""",
                  parameters("propName", property, "value", value));
          var result = tx.run(query);
          return result.list();
        });
  }

  public void createNode(final InfrastructureNode node) {
    write(
        tx -> {
          var result =
              tx.run(
                  "CREATE (n:Node $properties) RETURN n", parameters("properties", node.toProps()));
          return result.list();
        });
  }

  public void deleteNode(final UUID nodeId) {
    write(
        tx -> {
          tx.run(
              """
                  MATCH (n)-[r]-()
                          WHERE n.uuid = $uuid
                          DELETE r""",
              parameters("uuid", nodeId.toString()));
          var result =
              tx.run(
                  """
                      MATCH (n)
                              WHERE n.uuid = $uuid
                              DELETE n""",
                  parameters("uuid", nodeId.toString()));
          return result.list();
        });
  }

  public void createPath(final InfrastructureNodePath path) {
    write(
        tx -> {
          var result =
              tx.run(
                  """
                      MATCH
                        (a:Node),
                        (b:Node)
                        WHERE a.uuid = $fromUuid AND b.uuid = $toUuid
                      CREATE (a)-[r:CONNECTS]->(b)
                      RETURN type(r)""",
                  parameters("fromUuid", path.from().toString(), "toUuid", path.to().toString()));
          return result.list();
        });
  }

  public void deletePath(final InfrastructureNodePath path) {
    write(
        tx -> {
          var result =
              tx.run(
                  """
                      MATCH
                        (a:Node)-[r]->(b:Node)
                        WHERE a.uuid = $fromUuid AND b.uuid = $toUuid
                      DELETE r""",
                  parameters("fromUuid", path.from().toString(), "toUuid", path.to().toString()));
          return result.list();
        });
  }

  private <T> T read(final TransactionWork<T> run) {
    try (var session = driver.session()) {
      return session.readTransaction(run);
    }
  }

  private <T> T write(final TransactionWork<T> run) {
    try (var session = driver.session()) {
      return session.writeTransaction(run);
    }
  }
}
