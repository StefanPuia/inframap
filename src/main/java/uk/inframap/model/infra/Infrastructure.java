package uk.inframap.model.infra;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronaut.serde.annotation.Serdeable;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Serdeable
@JsonInclude(NON_NULL)
public record Infrastructure(List<InfrastructureNode> nodes, List<InfrastructureNodePath> paths) {}
