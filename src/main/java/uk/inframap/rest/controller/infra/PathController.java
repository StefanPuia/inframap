package uk.inframap.rest.controller.infra;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Post;
import javax.validation.Valid;
import uk.inframap.data.PathDelegate;
import uk.inframap.model.infra.InfrastructureNodePath;
import uk.inframap.rest.dto.infra.PathDto;

@Controller("/path")
public class PathController {
  private final PathDelegate delegate;

  public PathController(final PathDelegate delegate) {
    this.delegate = delegate;
  }

  @Post
  public HttpResponse<InfrastructureNodePath> createPath(final @Valid @Body PathDto createPath) {
    return HttpResponse.ok(delegate.createPath(createPath.toDomain()));
  }

  @Delete
  public HttpResponse<Void> deletePath(final @Valid @Body PathDto createPath) {
    delegate.deletePath(createPath.toDomain());
    return HttpResponse.accepted();
  }
}
