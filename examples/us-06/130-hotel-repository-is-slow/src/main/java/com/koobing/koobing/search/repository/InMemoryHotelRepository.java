package com.koobing.koobing.search.repository;

import com.koobing.koobing.search.HotelRepository;
import com.koobing.koobing.search.domain.Address;
import com.koobing.koobing.search.domain.Hotel;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class InMemoryHotelRepository implements HotelRepository {
    private final boolean available;

    public InMemoryHotelRepository(boolean isAvailable) {
        this.available = isAvailable;
    }

    public InMemoryHotelRepository() {
        this(true);
    }

    @Override
    public List<Hotel> findAvailableHotelsByZipcodeAndDates(String zipcode, LocalDate arrivalDate, LocalDate departureDate) {
        if (!available) {
            throw new IllegalStateException("Repository is down");
        }

        if (arrivalDate.equals(LocalDate.of(2024, 1, 1))) {
            return List.of(
                    new Hotel(1, "Elegance Hotel", new Address("25 RUE DU LOUVRE", "PARIS", "75001"), 10, 150, List.of("Free Wi-Fi", "Parking", "Complimentary Breakfast")),
                    new Hotel(2, "Charming Inn", new Address("21 RUE DU BOULOI", "PARIS", "75001"), 5, 120, List.of("Free Wi-Fi", "Swimming Pool", "Room Service"))
            );
        }

        return Collections.emptyList();
    }
}
