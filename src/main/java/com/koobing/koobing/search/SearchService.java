package com.koobing.koobing.search;

import com.koobing.koobing.search.domain.Hotel;
import com.koobing.koobing.utils.Either;

import java.time.LocalDate;
import java.util.List;

public interface SearchService {
    Either<SearchError, List<Hotel>> availableHostels(String zipcode, LocalDate arrivalDate, LocalDate departureDate);
}
