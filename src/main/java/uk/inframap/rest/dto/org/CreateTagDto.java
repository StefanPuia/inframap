package uk.inframap.rest.dto.org;

import io.micronaut.serde.annotation.Serdeable;
import javax.validation.constraints.NotBlank;

@Serdeable
public record CreateTagDto(@NotBlank String name) {
  public String toDomain() {
    return name();
  }
}
