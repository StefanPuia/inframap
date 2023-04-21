package uk.inframap.model.org;

import io.micronaut.serde.annotation.Serdeable;
import java.util.Map;
import java.util.UUID;
import org.neo4j.driver.internal.value.NodeValue;

@Serdeable
public record Organisation(UUID id, String name) {

  public static final String KEY_ID = "id";
  public static final String KEY_NAME = "name";

  public static Organisation from(final NodeValue node) {
    final var props = node.asMap();
    return new Organisation(
        UUID.fromString((String) props.get(KEY_ID)), (String) props.get(KEY_NAME));
  }

  public Map<String, Object> toProps() {
    return Map.of(KEY_ID, id.toString(), KEY_NAME, name);
  }
}
