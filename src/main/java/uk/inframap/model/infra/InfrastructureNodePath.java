package uk.inframap.model.infra;

import io.micronaut.serde.annotation.Serdeable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.neo4j.driver.internal.value.PathValue;
import org.neo4j.driver.types.Node;

@Serdeable
public record InfrastructureNodePath(UUID from, UUID to) {

  public static InfrastructureNodePath from(final PathValue path) {
    final List<Node> nodes =
        ((AbstractList<Node>) path.asPath().nodes())
            .stream()
                .filter(node -> ((AbstractList<String>) node.labels()).contains("Node"))
                .toList();
    if (nodes.size() != 2) {
      throw new IllegalStateException();
    }
    return new InfrastructureNodePath(
        UUID.fromString((String) nodes.get(0).asMap().get("uuid")),
        UUID.fromString((String) nodes.get(1).asMap().get("uuid")));
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
    final int[] codes = {from.hashCode(), to.hashCode()};
    Arrays.sort(codes);
    return Arrays.hashCode(codes);
  }

  public boolean isSelf() {
    return from.equals(to);
  }
}
