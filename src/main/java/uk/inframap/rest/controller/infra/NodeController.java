package uk.inframap.rest.controller.infra;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import java.util.UUID;
import uk.inframap.data.NodeDelegate;
import uk.inframap.model.infra.Infrastructure;
import uk.inframap.model.infra.InfrastructureNode;
import uk.inframap.rest.dto.CreateNodeDto;

@Controller("/node")
public class NodeController {
  private final NodeDelegate delegate;

  public NodeController(final NodeDelegate delegate) {
    this.delegate = delegate;
  }

  @Get
  public HttpResponse<Infrastructure> findAll() {
    return HttpResponse.ok().body(delegate.findAll());
  }

  @Get("{tagName}/{value}")
  public HttpResponse<Infrastructure> findNodesByTag(
      final @PathVariable("tagName") String tagName, final @PathVariable("value") String value) {
    return HttpResponse.ok().body(delegate.findByTag(tagName, value));
  }

  @Post
  public HttpResponse<InfrastructureNode> createNode(final @Body CreateNodeDto createNode) {
    return HttpResponse.ok(delegate.create(createNode.toNode()));
  }

  @Delete("{uuid}")
  public HttpResponse<Void> deleteNode(final @PathVariable("uuid") UUID nodeId) {
    delegate.delete(nodeId);
    return HttpResponse.accepted();
  }
}
