package com.koobing.koobing.search;

import com.koobing.koobing.search.domain.AvailableHotels;
import com.koobing.koobing.search.domain.Zipcode;
import com.koobing.koobing.utils.Either;

import java.time.LocalDate;

public interface SearchService {
    Either<SearchError, AvailableHotels> availableHostels(Zipcode zipcode, LocalDate arrivalDate, LocalDate departureDate);

    default Either<SearchError, AvailableHotels> availableHostels(String zipcode, LocalDate arrivalDate, LocalDate departureDate) {
        return this.availableHostels(new Zipcode(zipcode), arrivalDate, departureDate);
    }
}
