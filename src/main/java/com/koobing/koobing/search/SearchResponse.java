package com.koobing.koobing.search;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public sealed interface SearchResponse {
    record Found(List<HotelDto> hotels) implements SearchResponse {
    }

    record NotFound(@JsonProperty("search_criteria") SearchCriteriaDto criteria) implements SearchResponse {
    }
}
