package uk.inframap.model.infra;

import io.micronaut.serde.annotation.Serdeable;
import java.util.List;

@Serdeable
public record Infrastructure(List<InfrastructureNode> nodes, List<InfrastructureNodePath> paths) {}
