package uk.inframap.rest.controller.org;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import uk.inframap.data.OrganisationDelegate;
import uk.inframap.model.org.Organisation;
import uk.inframap.rest.dto.org.CreateOrgDto;
import uk.inframap.rest.dto.org.OrganisationWithConfigDto;

@Controller("/org")
public class OrganisationController {
  private final OrganisationDelegate delegate;

  public OrganisationController(final OrganisationDelegate delegate) {
    this.delegate = delegate;
  }

  @Get
  public HttpResponse<List<Organisation>> findAll() {
    return HttpResponse.ok(delegate.findAll());
  }

  @Get("/{orgId}")
  public HttpResponse<OrganisationWithConfigDto> find(final @PathVariable("orgId") UUID orgId) {
    return HttpResponse.ok(
        new OrganisationWithConfigDto(
            delegate.find(orgId), delegate.findAllTypes(orgId), delegate.findAllTags(orgId)));
  }

  @Post
  public HttpResponse<Organisation> create(final @Valid @Body CreateOrgDto createOrgDto) {
    return HttpResponse.ok(delegate.createOrganisation(createOrgDto.toDomain()));
  }
}
