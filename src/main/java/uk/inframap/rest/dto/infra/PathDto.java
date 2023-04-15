package uk.inframap.rest.dto.infra;

import io.micronaut.serde.annotation.Serdeable;
import java.util.UUID;
import uk.inframap.model.infra.InfrastructureNodePath;

@Serdeable
public record PathDto(UUID from, UUID to) {
  public InfrastructureNodePath toDomain() {
    return new InfrastructureNodePath(from, to);
  }
}
