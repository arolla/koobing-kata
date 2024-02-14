package com.koobing.koobing.search;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SearchCriteriaDto(
        String zipcode,
        @JsonProperty("arrival_date") String arrivalDate,
        @JsonProperty("departure_date") String departureDate
) {
}
