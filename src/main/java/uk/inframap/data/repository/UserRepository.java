package uk.inframap.data.repository;

import jakarta.inject.Singleton;
import java.util.List;
import java.util.UUID;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import uk.inframap.model.user.User;

import static org.neo4j.driver.Values.parameters;

@Singleton
public class UserRepository extends AbstractRepository {
  protected UserRepository(final Driver driver) {
    super(driver);
  }

  public void createUser(final User user) {
    write(tx -> tx.run("CREATE (u:User $properties)", parameters("properties", user.toProps())));
  }

  public Record find(final UUID userId) {
    return read(
        tx ->
            tx.run("MATCH (u:User {id: $userId}) RETURN u", parameters("userId", userId.toString()))
                .next());
  }

  public List<Record> findForOrg(final UUID orgId) {
    return read(
        tx ->
            tx.run(
                    "MATCH (u:User)-[:PART_OF]->(o:Organisation {id: $orgId}) RETURN u",
                    parameters("orgId", orgId.toString()))
                .list());
  }

  public void linkToOrganisation(final UUID userId, final UUID orgId) {
    write(
        tx ->
            tx.run(
                """
                    MATCH (u:User), (o:Organisation)
                      WHERE u.id = $userId AND o.id = $orgId
                    CREATE (u)-[:PART_OF]->(o)""",
                parameters("userId", userId.toString(), "orgId", orgId.toString())));
  }
}
