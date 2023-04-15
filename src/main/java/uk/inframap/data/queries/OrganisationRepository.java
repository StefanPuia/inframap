package uk.inframap.data.queries;

import jakarta.inject.Singleton;
import java.util.List;
import java.util.UUID;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import uk.inframap.model.org.Organisation;

import static org.neo4j.driver.Values.parameters;

@Singleton
public class OrganisationRepository extends AbstractRepository {
  protected OrganisationRepository(final Driver driver) {
    super(driver);
  }

  public List<Record> findAll() {
    return read(tx -> tx.run("MATCH (o:Organisation) RETURN o").list());
  }

  public void createOrg(final Organisation organisation) {
    write(
        tx ->
            tx.run(
                    "CREATE (o:Organisation $properties) RETURN o",
                    parameters("properties", organisation.toProps()))
                .list());
  }

  public List<Record> findOrgInfraTypes(final UUID organisationId) {
    return read(
        tx ->
            tx.run(
                    "MATCH (o:Organisation)-[:HAS_TYPE]->(t:InfrastructureType) where o.id = $orgId return t.name",
                    parameters("orgId", organisationId.toString()))
                .list());
  }

  public void createOrgInfraType(final UUID organisationId, final String type) {
    write(
        tx ->
            tx.run(
                    """
                match (o:Organisation)
                where o.id = $orgId
                create (o)-[:HAS_TYPE]->(t:InfrastructureType { name: $typeName });
                """,
                    parameters("orgId", organisationId.toString(), "typeName", type))
                .list());
  }

  public List<Record> findOrgInfraTags(final UUID organisationId) {
    return read(
        tx ->
            tx.run(
                    "MATCH (o:Organisation)-[:HAS_TAG]->(t:InfrastructureTag) where o.id = $orgId return t.name",
                    parameters("orgId", organisationId.toString()))
                .list());
  }

  public void createOrgInfraTag(final UUID organisationId, final String tag) {
    write(
        tx ->
            tx.run(
                    """
                match (o:Organisation)
                where o.id = $orgId
                create (o)-[:HAS_TAG]->(t:InfrastructureTag { name: $tagName });
                """,
                    parameters("orgId", organisationId.toString(), "tagName", tag))
                .list());
  }
}
