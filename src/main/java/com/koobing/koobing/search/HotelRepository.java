package com.koobing.koobing.search;

import com.koobing.koobing.search.domain.Hotel;

import java.time.LocalDate;
import java.util.List;

public interface HotelRepository {
    List<Hotel> findAvailableHotelsByZipcodeAndDates(String zipcode, LocalDate arrivalDate, LocalDate departureDate);
}
