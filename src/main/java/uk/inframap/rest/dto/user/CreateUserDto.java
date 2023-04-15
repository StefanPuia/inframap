package uk.inframap.rest.dto.user;

import io.micronaut.serde.annotation.Serdeable;
import java.util.UUID;
import uk.inframap.model.user.User;

@Serdeable
public record CreateUserDto(String username) {

  public User toDomain() {
    return new User(UUID.randomUUID(), username);
  }
}
