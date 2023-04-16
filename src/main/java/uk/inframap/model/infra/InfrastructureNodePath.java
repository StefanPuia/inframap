package uk.inframap.model.infra;

import io.micronaut.serde.annotation.Serdeable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.neo4j.driver.internal.value.PathValue;
import org.neo4j.driver.types.Node;

import static uk.inframap.model.infra.InfrastructureNode.KEY_ID;

@Serdeable
public record InfrastructureNodePath(UUID source, UUID target) {

  public static InfrastructureNodePath from(final PathValue path) {
    final List<Node> nodes =
        ((AbstractList<Node>) path.asPath().nodes())
            .stream()
                .filter(node -> ((AbstractList<String>) node.labels()).contains("Node"))
                .toList();
    if (nodes.size() < 2) {
      throw new IllegalStateException();
    }
    return new InfrastructureNodePath(
        UUID.fromString((String) nodes.get(0).asMap().get(KEY_ID)),
        UUID.fromString((String) nodes.get(1).asMap().get(KEY_ID)));
  }

  @Override
  public boolean equals(final Object compare) {
    if (compare instanceof InfrastructureNodePath) {
      return compare == this || compare.hashCode() == hashCode();
    }
    return false;
  }

  @Override
  public int hashCode() {
    final int[] codes = {source.hashCode(), target.hashCode()};
    Arrays.sort(codes);
    return Arrays.hashCode(codes);
  }

  public boolean isSelf() {
    return source.equals(target);
  }
}
