package uk.inframap.rest.controller.user;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import java.util.UUID;
import uk.inframap.data.UserDelegate;
import uk.inframap.rest.dto.user.CreateUserDto;

@Controller("/user")
public class UserController {

  private final UserDelegate delegate;

  public UserController(final UserDelegate delegate) {
    this.delegate = delegate;
  }

  @Post
  public HttpResponse<Void> createUser(final @Body CreateUserDto createUserDto) {
    delegate.createUser(createUserDto.toDomain());
    return HttpResponse.ok();
  }

  @Post("/{userId}/{orgId}")
  public HttpResponse<Void> linkToOrg(
      final @PathVariable("userId") UUID userId, final @PathVariable UUID orgId) {
    delegate.linkUserToOrganisation(userId, orgId);
    return HttpResponse.accepted();
  }
}
