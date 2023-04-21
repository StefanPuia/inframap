package uk.inframap.model.org;

import io.micronaut.serde.annotation.Serdeable;
import java.util.Map;
import org.neo4j.driver.internal.value.NodeValue;

@Serdeable
public record InfrastructureType(String name, String image) {

  public static final String KEY_NAME = "name";
  public static final String KEY_IMAGE = "image";

  public static InfrastructureType from(final NodeValue node) {
    final var props = node.asMap();
    return new InfrastructureType((String) props.get(KEY_NAME), (String) props.get(KEY_IMAGE));
  }

  public Map<String, Object> toProps() {
    return Map.of(KEY_NAME, name, KEY_IMAGE, image);
  }
}
