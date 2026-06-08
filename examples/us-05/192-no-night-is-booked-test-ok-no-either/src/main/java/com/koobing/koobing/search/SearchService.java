package com.koobing.koobing.search;

import com.koobing.koobing.search.domain.AvailableHotels;
import com.koobing.koobing.utils.ServiceResponse;

import java.time.LocalDate;

public interface SearchService {
    ServiceResponse<AvailableHotels, SearchError> availableHostels(String zipcode, LocalDate arrivalDate, LocalDate departureDate);
}
