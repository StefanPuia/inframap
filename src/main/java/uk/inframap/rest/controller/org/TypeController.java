package uk.inframap.rest.controller.org;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import java.util.List;
import java.util.UUID;
import uk.inframap.data.OrganisationDelegate;
import uk.inframap.rest.dto.org.CreateTypeDto;

@Controller("/org/{orgId}/type")
public class TypeController {
  private final OrganisationDelegate delegate;

  public TypeController(final OrganisationDelegate delegate) {
    this.delegate = delegate;
  }

  @Get
  public HttpResponse<List<String>> getAll(final @PathVariable("orgId") UUID organisationId) {
    return HttpResponse.ok(delegate.findAllTypes(organisationId));
  }

  @Post
  public HttpResponse<String> createType(
      final @PathVariable("orgId") UUID organisationId, final @Body CreateTypeDto createTypeDto) {
    return HttpResponse.ok(
        delegate.createOrganisationType(organisationId, createTypeDto.toDomain()));
  }

  @Delete("/{type}")
  public HttpResponse<Void> deleteType(
      final @PathVariable("orgId") UUID organisationId, final @PathVariable("type") String tagName) {
    delegate.deleteType(organisationId, tagName);
    return HttpResponse.accepted();
  }
}
