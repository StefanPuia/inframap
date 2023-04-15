package uk.inframap.rest.dto.org;

import io.micronaut.serde.annotation.Serdeable;
import java.util.UUID;
import uk.inframap.model.org.Organisation;

@Serdeable
public record CreateOrgDto(String name) {

  public Organisation toDomain() {
    return new Organisation(UUID.randomUUID(), name);
  }
}
