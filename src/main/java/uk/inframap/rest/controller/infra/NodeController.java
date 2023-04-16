package uk.inframap.rest.controller.infra;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import java.util.UUID;
import javax.validation.Valid;
import uk.inframap.data.NodeDelegate;
import uk.inframap.model.infra.Infrastructure;
import uk.inframap.model.infra.InfrastructureNode;
import uk.inframap.rest.dto.infra.CreateNodeDto;

@Controller("/org/{orgId}/node")
public class NodeController {
  private final NodeDelegate delegate;

  public NodeController(final NodeDelegate delegate) {
    this.delegate = delegate;
  }

  @Get
  public HttpResponse<Infrastructure> findAll(final @PathVariable("orgId") UUID orgId) {
    return HttpResponse.ok().body(delegate.findAllForOrg(orgId));
  }

  @Get("{tagName}/{value}")
  public HttpResponse<Infrastructure> findNodesByTag(
      final @PathVariable("orgId") UUID orgId,
      final @PathVariable("tagName") String tagName,
      final @PathVariable("value") String value) {
    return HttpResponse.ok().body(delegate.findByTag(orgId, tagName, value));
  }

  @Post
  public HttpResponse<InfrastructureNode> createNode(
      final @PathVariable("orgId") UUID orgId, final @Valid @Body CreateNodeDto createNode) {
    return HttpResponse.ok(delegate.create(orgId, createNode.toDomain()));
  }

  @Delete("{uuid}")
  public HttpResponse<Void> deleteNode(
      final @PathVariable("orgId") UUID orgId, final @PathVariable("uuid") UUID nodeId) {
    delegate.delete(orgId, nodeId);
    return HttpResponse.accepted();
  }
}
