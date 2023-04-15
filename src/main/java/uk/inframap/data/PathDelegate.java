package uk.inframap.data;

import jakarta.inject.Singleton;
import java.util.List;
import org.neo4j.driver.Record;
import org.neo4j.driver.internal.value.PathValue;
import uk.inframap.data.repository.InfrastructureRepository;
import uk.inframap.model.infra.InfrastructureNodePath;

import static java.util.function.Predicate.not;

@Singleton
public class PathDelegate {
  private final InfrastructureRepository repository;

  public PathDelegate(final InfrastructureRepository repository) {
    this.repository = repository;
  }

  static List<InfrastructureNodePath> pathsFromResults(final List<Record> results) {
    return results.stream()
        .map(r -> (PathValue) r.get(1))
        .filter(path -> path.asPath().length() > 0)
        .map(InfrastructureNodePath::from)
        .filter(not(InfrastructureNodePath::isSelf))
        .distinct()
        .toList();
  }

  public InfrastructureNodePath createPath(final InfrastructureNodePath createPath) {
    repository.createPath(createPath);
    return createPath;
  }

  public void deletePath(final InfrastructureNodePath path) {
    repository.deletePath(path);
  }
}
