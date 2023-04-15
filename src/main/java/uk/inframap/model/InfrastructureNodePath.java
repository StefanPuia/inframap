package uk.inframap.model;

import io.micronaut.serde.annotation.Serdeable;
import java.util.Arrays;
import java.util.UUID;
import org.neo4j.driver.internal.value.PathValue;

@Serdeable
public record InfrastructureNodePath(UUID from, UUID to) {

  public static InfrastructureNodePath from(final PathValue path) {
    return new InfrastructureNodePath(
        UUID.fromString((String) path.asPath().start().asMap().get("uuid")),
        UUID.fromString((String) path.asPath().end().asMap().get("uuid")));
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
