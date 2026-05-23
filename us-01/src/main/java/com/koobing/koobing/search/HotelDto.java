package com.koobing.koobing.search;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record HotelDto(
        int id,
        String name,
        String address,
        @JsonProperty("available_rooms")
        int availableRooms,
        int price,
        List<String> amenities
) {
}

