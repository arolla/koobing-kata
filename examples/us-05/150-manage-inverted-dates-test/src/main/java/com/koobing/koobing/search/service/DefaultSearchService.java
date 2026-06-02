package com.koobing.koobing.search.service;

import com.koobing.koobing.search.HotelRepository;
import com.koobing.koobing.search.SearchService;
import com.koobing.koobing.search.domain.Hotel;

import java.time.LocalDate;
import java.util.List;

public class DefaultSearchService implements SearchService {
    private final HotelRepository hotelRepository;

    public DefaultSearchService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<Hotel> availableHostels(String zipcode, LocalDate arrivalDate, LocalDate departureDate) {
        return hotelRepository.findAvailableHotelsByZipcodeAndDates(zipcode, arrivalDate, departureDate);
    }
}
