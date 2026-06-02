package com.koobing.koobing.search.repository;

import com.koobing.koobing.search.HotelRepository;
import com.koobing.koobing.search.domain.Hotel;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class DbHotelRepository implements HotelRepository {

    @Override
    public List<Hotel> findAvailableHotelsByZipcodeAndDates(String zipcode, LocalDate arrivalDate, LocalDate departureDate) {
        return Collections.emptyList();
    }
}
