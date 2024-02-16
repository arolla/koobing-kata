package com.koobing.koobing.search.service;

import com.koobing.koobing.search.HotelRepository;
import com.koobing.koobing.search.SearchError;
import com.koobing.koobing.search.SearchService;
import com.koobing.koobing.search.domain.Hotel;
import com.koobing.koobing.utils.Either;

import java.time.LocalDate;
import java.util.List;

public class DefaultSearchService implements SearchService {
    private final HotelRepository hotelRepository;

    public DefaultSearchService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Either<SearchError, List<Hotel>> availableHostels(String zipcode, LocalDate arrivalDate, LocalDate departureDate) {
        if (arrivalDate.equals(departureDate)) {
            return Either.left(new SearchError("A booking must contain at least one night."));
        }

        if (departureDate.isBefore(arrivalDate)) {
            return Either.right(hotelRepository.findAvailableHotelsByZipcodeAndDates(zipcode, departureDate, arrivalDate));
        }
        return Either.right(hotelRepository.findAvailableHotelsByZipcodeAndDates(zipcode, arrivalDate, departureDate));
    }
}
