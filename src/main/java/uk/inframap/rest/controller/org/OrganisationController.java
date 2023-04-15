package uk.inframap.rest.controller.org;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import java.util.List;
import uk.inframap.data.OrganisationDelegate;
import uk.inframap.model.org.Organisation;
import uk.inframap.rest.dto.CreateOrgDto;

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

  @Post
  public HttpResponse<Organisation> create(final @Body CreateOrgDto createOrgDto) {
    return HttpResponse.ok(delegate.createOrganisation(createOrgDto.toOrganisation()));
  }
}
