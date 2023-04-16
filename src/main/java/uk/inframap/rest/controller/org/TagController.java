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
import javax.validation.Valid;
import uk.inframap.data.OrganisationDelegate;
import uk.inframap.rest.dto.org.CreateTagDto;

@Controller("/org/{orgId}/tag")
public class TagController {
  private final OrganisationDelegate delegate;

  public TagController(final OrganisationDelegate delegate) {
    this.delegate = delegate;
  }

  @Get
  public HttpResponse<List<String>> getAll(final @PathVariable("orgId") UUID organisationId) {
    return HttpResponse.ok(delegate.findAllTags(organisationId));
  }

  @Post
  public HttpResponse<String> createType(
      final @PathVariable("orgId") UUID organisationId, final @Valid @Body CreateTagDto createTagDto) {
    return HttpResponse.ok(delegate.createOrganisationTag(organisationId, createTagDto.toDomain()));
  }

  @Delete("/{tag}")
  public HttpResponse<Void> deleteTag(
      final @PathVariable("orgId") UUID organisationId, final @PathVariable("tag") String tagName) {
    delegate.deleteTag(organisationId, tagName);
    return HttpResponse.accepted();
  }
}
