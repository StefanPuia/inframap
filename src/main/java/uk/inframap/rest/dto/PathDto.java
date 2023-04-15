package uk.inframap.rest.dto;

import io.micronaut.serde.annotation.Serdeable;
import java.util.UUID;
import uk.inframap.model.InfrastructureNodePath;

@Serdeable
public record PathDto(UUID from, UUID to) {
  public InfrastructureNodePath toPath() {
    return new InfrastructureNodePath(from, to);
  }
}
