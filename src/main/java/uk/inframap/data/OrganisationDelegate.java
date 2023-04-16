package uk.inframap.data;

import jakarta.inject.Singleton;
import java.util.List;
import java.util.UUID;
import org.neo4j.driver.internal.value.NodeValue;
import uk.inframap.data.repository.OrganisationRepository;
import uk.inframap.model.org.Organisation;

@Singleton
public class OrganisationDelegate {
  private final OrganisationRepository repository;
  private final RecordMapper recordMapper;

  public OrganisationDelegate(
      final OrganisationRepository repository, final RecordMapper recordMapper) {
    this.repository = repository;
    this.recordMapper = recordMapper;
  }

  public Organisation createOrganisation(final Organisation organisation) {
    repository.createOrg(organisation);
    return organisation;
  }

  public List<Organisation> findAll() {
    return recordMapper.mapValues(repository.findAll(), Organisation::from);
  }

  public List<String> findAllTypes(final UUID organisationId) {
    return recordMapper.mapStringList(repository.findOrgInfraTypes(organisationId));
  }

  public String createOrganisationType(final UUID organisationId, final String type) {
    repository.createOrgInfraType(organisationId, type);
    return type;
  }

  public List<String> findAllTags(final UUID organisationId) {
    return recordMapper.mapStringList(repository.findOrgInfraTags(organisationId));
  }

  public String createOrganisationTag(final UUID organisationId, final String name) {
    repository.createOrgInfraTag(organisationId, name);
    return name;
  }

  public void deleteTag(final UUID organisationId, final String tagName) {
    repository.deleteTag(organisationId, tagName);
  }

  public void deleteType(final UUID organisationId, final String typeName) {
    repository.deleteType(organisationId, typeName);
  }

  public Organisation find(final UUID orgId) {
    return Organisation.from((NodeValue) repository.findById(orgId).get(0));
  }
}
