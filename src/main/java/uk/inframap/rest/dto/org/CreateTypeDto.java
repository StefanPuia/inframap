package uk.inframap.rest.dto.org;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record CreateTypeDto(String name) {
  public String toDomain() {
    return name();
  }
}
