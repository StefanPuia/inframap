package uk.inframap.data;

import jakarta.inject.Singleton;
import java.util.List;
import java.util.UUID;
import org.neo4j.driver.Record;
import org.neo4j.driver.internal.value.NodeValue;
import uk.inframap.data.queries.InfrastructureRepository;
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

  public Infrastructure findAll() {
    final var results = repository.findAll();
    return new Infrastructure(nodesFromResults(results), pathsFromResults(results));
  }

  public Infrastructure findByTag(final String tag, final String value) {
    final var results = repository.findByProperty(tag, value);
    return new Infrastructure(nodesFromResults(results), pathsFromResults(results));
  }

  public InfrastructureNode create(final InfrastructureNode createNode) {
    repository.createNode(createNode);
    return createNode;
  }

  public void delete(final UUID nodeId) {
    repository.deleteNode(nodeId);
  }
}
