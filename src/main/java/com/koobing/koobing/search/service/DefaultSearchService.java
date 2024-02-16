package com.koobing.koobing.search.service;

import com.koobing.koobing.search.HotelRepository;
import com.koobing.koobing.search.SearchError;
import com.koobing.koobing.search.SearchService;
import com.koobing.koobing.search.domain.AvailableHotels;
import com.koobing.koobing.utils.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class DefaultSearchService implements SearchService {
    private static final Logger log = LoggerFactory.getLogger(DefaultSearchService.class);
    private final HotelRepository hotelRepository;

    public DefaultSearchService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Either<SearchError, AvailableHotels> availableHostels(String zipcode, LocalDate arrivalDate, LocalDate departureDate) {
        if (arrivalDate.equals(departureDate)) {
            return Either.left(SearchError.AT_LEAST_ONE_NIGHT);
        }

        try {
            CompletableFuture<Either<SearchError, AvailableHotels>> result = CompletableFuture.supplyAsync(() -> {
                if (departureDate.isBefore(arrivalDate)) {
                    var hotels = new AvailableHotels(hotelRepository.findAvailableHotelsByZipcodeAndDates(zipcode, departureDate, arrivalDate));
                    return Either.right(hotels);
                }

                var hotels = new AvailableHotels(hotelRepository.findAvailableHotelsByZipcodeAndDates(zipcode, arrivalDate, departureDate));
                return Either.right(hotels);
            });

            return result.get(500, TimeUnit.MILLISECONDS);

        } catch (TimeoutException e) {
            log.warn(e.getMessage(), e);
            return Either.right(new AvailableHotels(Collections.emptyList()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Either.right(new AvailableHotels(Collections.emptyList()));
        }
    }
}
