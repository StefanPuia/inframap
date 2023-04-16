package uk.inframap.data;

import jakarta.inject.Singleton;
import java.util.List;
import java.util.UUID;
import org.neo4j.driver.Record;
import org.neo4j.driver.internal.value.NodeValue;
import uk.inframap.data.repository.InfrastructureRepository;
import uk.inframap.model.infra.Infrastructure;
import uk.inframap.model.infra.InfrastructureNode;

import static uk.inframap.data.PathDelegate.pathsFromResults;

@Singleton
public class NodeDelegate {
  private final InfrastructureRepository repository;

  public NodeDelegate(final InfrastructureRepository repository) {
    this.repository = repository;
  }

  static List<InfrastructureNode> nodesFromResults(final List<Record> results) {
    return results.stream()
        .map(r -> (NodeValue) r.get(0))
        .map(InfrastructureNode::from)
        .distinct()
        .toList();
  }

  public Infrastructure findAllForOrg(final UUID orgId) {
    final var results = repository.findAllForOrg(orgId);
    return new Infrastructure(nodesFromResults(results), pathsFromResults(results));
  }

  public Infrastructure findByTag(final UUID orgId, final String tag, final String value) {
    final var results = repository.findByProperty(orgId,tag, value);
    return new Infrastructure(nodesFromResults(results), pathsFromResults(results));
  }

  public InfrastructureNode create(final UUID orgId, final InfrastructureNode createNode) {
    repository.createNode(orgId, createNode);
    return createNode;
  }

  public void delete(final UUID orgId, final UUID nodeId) {
    repository.deleteNode(orgId, nodeId);
  }
}
