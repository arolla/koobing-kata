package com.koobing.koobing.search;

import com.koobing.koobing.search.domain.AvailableHotels;
import com.koobing.koobing.utils.Either;

import java.time.LocalDate;

public interface SearchService {
    Either<SearchError, AvailableHotels> availableHostels(String zipcode, LocalDate arrivalDate, LocalDate departureDate);
}
