package uk.inframap.rest.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Post;
import uk.inframap.data.PathDelegate;
import uk.inframap.model.InfrastructureNodePath;
import uk.inframap.rest.dto.PathDto;

@Controller("/path")
public class PathController {
  private final PathDelegate delegate;

  public PathController(final PathDelegate delegate) {
    this.delegate = delegate;
  }

  @Post
  public HttpResponse<InfrastructureNodePath> createPath(final @Body PathDto createPath) {
    return HttpResponse.ok(delegate.createPath(createPath.toPath()));
  }

  @Delete
  public HttpResponse<Void> deletePath(final @Body PathDto createPath) {
    delegate.deletePath(createPath.toPath());
    return HttpResponse.accepted();
  }
}
