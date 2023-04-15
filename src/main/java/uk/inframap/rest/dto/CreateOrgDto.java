package uk.inframap.rest.dto;

import io.micronaut.serde.annotation.Serdeable;
import java.util.UUID;
import uk.inframap.model.org.Organisation;

@Serdeable
public record CreateOrgDto(String name) {

  public Organisation toOrganisation() {
    return new Organisation(UUID.randomUUID(), name);
  }
}
