package com.koobing.koobing.search.domain;

import java.util.List;

public record Hotel(
        int id,
        String name,
        Address address,
        int availableRooms,
        int price,
        List<String> amenities

) {
}
