package uk.inframap.rest.dto.org;

import io.micronaut.serde.annotation.Serdeable;
import java.util.List;
import uk.inframap.model.org.InfrastructureType;
import uk.inframap.model.org.Organisation;

@Serdeable
public record OrganisationWithConfigDto(
    Organisation organisation, List<InfrastructureType> types, List<String> tags) {}
