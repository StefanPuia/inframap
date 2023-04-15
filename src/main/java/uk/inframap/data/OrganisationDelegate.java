package uk.inframap.data;

import jakarta.inject.Singleton;
import java.util.List;
import java.util.UUID;
import org.neo4j.driver.Record;
import org.neo4j.driver.Value;
import org.neo4j.driver.internal.value.NodeValue;
import uk.inframap.data.queries.OrganisationRepository;
import uk.inframap.model.org.Organisation;

@Singleton
public class OrganisationDelegate {
  private final OrganisationRepository repository;

  public OrganisationDelegate(final OrganisationRepository repository) {
    this.repository = repository;
  }

  static List<Organisation> organisationsFromResults(final List<Record> results) {
    return results.stream().map(r -> (NodeValue) r.get(0)).map(Organisation::from).toList();
  }

  public Organisation createOrganisation(final Organisation organisation) {
    repository.createOrg(organisation);
    return organisation;
  }

  public List<Organisation> findAll() {
    return organisationsFromResults(repository.findAll());
  }

  public List<String> findAllTypes(final UUID organisationId) {
    return repository.findOrgInfraTypes(organisationId).stream()
        .map(r -> r.get(0))
        .map(Value::asString)
        .toList();
  }

  public String createOrganisationType(final UUID organisationId, final String type) {
    repository.createOrgInfraType(organisationId, type);
    return type;
  }
}
