package uk.inframap.rest.dto.infra;

import io.micronaut.serde.annotation.Serdeable;
import java.util.Map;
import java.util.UUID;
import uk.inframap.model.infra.InfrastructureNode;

@Serdeable
public record CreateNodeDto(String name, String type, Map<String, Object> tags) {
  public InfrastructureNode toDomain() {
    return new InfrastructureNode(UUID.randomUUID(), name, type, tags);
  }
}
