package com.koobing.koobing.search.service;

import com.koobing.koobing.search.HotelRepository;
import com.koobing.koobing.search.SearchError;
import com.koobing.koobing.search.SearchService;
import com.koobing.koobing.search.domain.AvailableHotels;
import com.koobing.koobing.utils.Either;

import java.time.LocalDate;

public class DefaultSearchService implements SearchService {
    private final HotelRepository hotelRepository;

    public DefaultSearchService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Either<SearchError, AvailableHotels> availableHostels(String zipcode, LocalDate arrivalDate, LocalDate departureDate) {
        if (arrivalDate.equals(departureDate)) {
            return Either.left(new SearchError("A booking must contain at least one night."));
        }

        if (departureDate.isBefore(arrivalDate)) {
            var hotels = new AvailableHotels(hotelRepository.findAvailableHotelsByZipcodeAndDates(zipcode, departureDate, arrivalDate));
            return Either.right(hotels);
        }

        var hotels = new AvailableHotels(hotelRepository.findAvailableHotelsByZipcodeAndDates(zipcode, arrivalDate, departureDate));
        return Either.right(hotels);
    }
}
