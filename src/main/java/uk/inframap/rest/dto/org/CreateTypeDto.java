package uk.inframap.rest.dto.org;

import io.micronaut.serde.annotation.Serdeable;
import javax.validation.constraints.NotBlank;
import uk.inframap.model.org.InfrastructureType;

@Serdeable
public record CreateTypeDto(@NotBlank String name, @NotBlank String image) {
  public InfrastructureType toDomain() {
    return new InfrastructureType(name, image);
  }
}
