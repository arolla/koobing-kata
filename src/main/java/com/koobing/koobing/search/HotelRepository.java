package com.koobing.koobing.search;

import com.koobing.koobing.search.domain.Hotel;
import com.koobing.koobing.search.domain.Zipcode;

import java.time.LocalDate;
import java.util.List;

public interface HotelRepository {
    List<Hotel> findAvailableHotelsByZipcodeAndDates(Zipcode zipcode, LocalDate arrivalDate, LocalDate departureDate);
}
