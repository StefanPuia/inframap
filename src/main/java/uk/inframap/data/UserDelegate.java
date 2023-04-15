package uk.inframap.data;

import jakarta.inject.Singleton;
import java.util.UUID;
import uk.inframap.data.repository.UserRepository;
import uk.inframap.model.user.User;

@Singleton
public class UserDelegate {
  private final UserRepository repository;

  public UserDelegate(final UserRepository repository) {
    this.repository = repository;
  }

  public void createUser(final User user) {
    repository.createUser(user);
  }

  public void linkUserToOrganisation(final UUID userId, final UUID orgId) {
    repository.linkToOrganisation(userId, orgId);
  }
}
