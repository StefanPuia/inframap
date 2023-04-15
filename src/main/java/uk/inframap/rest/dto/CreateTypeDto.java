package uk.inframap.rest.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record CreateTypeDto(String name) {}
