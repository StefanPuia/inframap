package uk.inframap.rest.dto;

import io.micronaut.serde.annotation.Serdeable;
import java.util.Map;
import java.util.UUID;
import uk.inframap.model.InfrastructureNode;

@Serdeable
public record CreateNodeDto(String name, String type, Map<String, Object> tags) {
  public InfrastructureNode toNode() {
    return new InfrastructureNode(UUID.randomUUID(), name, type, tags);
  }
}
