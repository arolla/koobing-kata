package com.koobing.koobing.search.domain;

import java.util.List;

public record AvailableHotels(List<Hotel> hotels) {

    public AvailableHotels(List<Hotel> hotels) {
        // copyOf -> unmodifiable list
        this.hotels = List.copyOf(hotels);
    }

    public boolean isEmpty() {
        return hotels.isEmpty();
    }
}
