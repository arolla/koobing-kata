package com.koobing.koobing.search.service;

import com.koobing.koobing.search.SearchService;
import com.koobing.koobing.search.domain.Address;
import com.koobing.koobing.search.domain.Hotel;

import java.time.LocalDate;
import java.util.List;

public class DefaultSearchService implements SearchService {
    @Override
    public List<Hotel> availableHostels(String zipcode, LocalDate arrivalDate, LocalDate departureDate) {
        return List.of(
                new Hotel(1, "Elegance Hotel", new Address("25 RUE DU LOUVRE", "PARIS", "75001"), 10, 150, List.of("Free Wi-Fi", "Parking", "Complimentary Breakfast")),
                new Hotel(2, "Charming Inn", new Address("21 RUE DU BOULOI", "PARIS", "75001"), 5, 120, List.of("Free Wi-Fi", "Swimming Pool", "Room Service"))
        );
    }
}
