package com.koobing.koobing.search.domain;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public record AvailableHotels(List<Hotel> hotels) implements Iterable<Hotel> {

    public AvailableHotels(List<Hotel> hotels) {
        if (hotels == null) {
            this.hotels = Collections.emptyList();
        } else {
            // copyOf -> unmodifiable list
            this.hotels = List.copyOf(hotels);
        }
    }

    public boolean isEmpty() {
        return hotels.isEmpty();
    }

    @Override
    public Iterator<Hotel> iterator() {
        return hotels.iterator();
    }
}
