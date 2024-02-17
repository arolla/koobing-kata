package com.koobing.koobing.search.service;

import com.koobing.koobing.search.HotelRepository;
import com.koobing.koobing.search.SearchError;
import com.koobing.koobing.search.SearchService;
import com.koobing.koobing.search.domain.AvailableHotels;
import com.koobing.koobing.search.domain.Zipcode;
import com.koobing.koobing.utils.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class DefaultSearchService implements SearchService {
    private static final Logger log = LoggerFactory.getLogger(DefaultSearchService.class);

    private final HotelRepository hotelRepository;

    public DefaultSearchService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Either<SearchError, AvailableHotels> availableHostels(Zipcode zipcode, LocalDate arrivalDate, LocalDate departureDate) {
        log.debug("zipcode: {}, arrivalDate: {}, departureDate: {}", zipcode, arrivalDate, departureDate);
        if (arrivalDate.equals(departureDate)) {
            log.debug("arrivalDate and departureDate must be different");
            return Either.left(SearchError.AT_LEAST_ONE_NIGHT);
        }

        if (departureDate.isBefore(arrivalDate)) {
            log.debug("arrivalDate and departureDate are inverted");
            var hotels = new AvailableHotels(hotelRepository.findAvailableHotelsByZipcodeAndDates(zipcode, departureDate, arrivalDate));
            return Either.right(hotels);
        }

        var hotels = new AvailableHotels(hotelRepository.findAvailableHotelsByZipcodeAndDates(zipcode, arrivalDate, departureDate));
        return Either.right(hotels);
    }
}
