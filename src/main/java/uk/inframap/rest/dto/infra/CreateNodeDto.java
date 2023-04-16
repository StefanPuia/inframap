package uk.inframap.rest.dto.infra;

import io.micronaut.serde.annotation.Serdeable;
import java.util.Map;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import uk.inframap.model.infra.InfrastructureNode;

@Serdeable
public record CreateNodeDto(
    @NotBlank String name, @NotBlank String type, @NotNull Map<String, Object> tags) {
  public InfrastructureNode toDomain() {
    return new InfrastructureNode(UUID.randomUUID(), name, type, tags);
  }
}
