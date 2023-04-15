package uk.inframap.model;

import io.micronaut.serde.annotation.Serdeable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.neo4j.driver.internal.value.NodeValue;

@Serdeable
public record InfrastructureNode(UUID id, String name, String type, Map<String, Object> tags) {

  public static final String KEY_UUID = "uuid";
  public static final String KEY_NAME = "name";
  public static final String KEY_TYPE = "type";

  public static InfrastructureNode from(final NodeValue node) {
    final var props = node.asMap();
    final var visibleProps = new HashMap<>(props);
    visibleProps.remove(KEY_UUID);
    visibleProps.remove(KEY_NAME);
    visibleProps.remove(KEY_TYPE);
    return new InfrastructureNode(
        UUID.fromString((String) props.get(KEY_UUID)),
        (String) props.get(KEY_NAME),
        (String) props.get(KEY_TYPE),
        visibleProps);
  }

  public Map<String, Object> toProps() {
    final Map<String, Object> map = new HashMap<>(tags);
    map.put(KEY_UUID, id.toString());
    map.put(KEY_NAME, name);
    map.put(KEY_TYPE, type);
    return map;
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
