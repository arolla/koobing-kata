package com.koobing.koobing.search;

import com.koobing.koobing.search.domain.Address;
import com.koobing.koobing.search.domain.AvailableHotels;
import com.koobing.koobing.search.domain.Hotel;
import com.koobing.koobing.search.repository.InMemoryHotelRepository;
import com.koobing.koobing.search.service.DefaultSearchService;
import com.koobing.koobing.utils.Either;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SearchServiceTests {

    @Test
    @DisplayName("Search hotel")
    void searchHotel() {
        HotelRepository hotelRepository = new InMemoryHotelRepository();
        SearchService searchService = new DefaultSearchService(hotelRepository);
        Either<SearchError, AvailableHotels> hotels = searchService.availableHostels("75001",
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 2));

        assertThat(hotels.right().hotels()).containsExactly(
                new Hotel(1, "Elegance Hotel", new Address("25 RUE DU LOUVRE", "PARIS", "75001"), 10, 150, List.of("Free Wi-Fi", "Parking", "Complimentary Breakfast")),
                new Hotel(2, "Charming Inn", new Address("21 RUE DU BOULOI", "PARIS", "75001"), 5, 120, List.of("Free Wi-Fi", "Swimming Pool", "Room Service"))
        );
    }

    @Test
    @DisplayName("Search hotel by inverting arrival and departure dates")
    void searchHotelWithInvertedDates() {
        HotelRepository hotelRepository = new InMemoryHotelRepository();
        SearchService searchService = new DefaultSearchService(hotelRepository);
        Either<SearchError, AvailableHotels> hotels = searchService.availableHostels("75001",
                LocalDate.of(2024, 1, 2),
                LocalDate.of(2024, 1, 1));

        assertThat(hotels.right().hotels()).containsExactly(
                new Hotel(1, "Elegance Hotel", new Address("25 RUE DU LOUVRE", "PARIS", "75001"), 10, 150, List.of("Free Wi-Fi", "Parking", "Complimentary Breakfast")),
                new Hotel(2, "Charming Inn", new Address("21 RUE DU BOULOI", "PARIS", "75001"), 5, 120, List.of("Free Wi-Fi", "Swimming Pool", "Room Service"))
        );
    }

    @Test
    @DisplayName("Search hotel for no night")
    void searchHotelWithoutOneNightAtLeast() {
        HotelRepository hotelRepository = new InMemoryHotelRepository();
        SearchService searchService = new DefaultSearchService(hotelRepository);
        Either<SearchError, AvailableHotels> hotels = searchService.availableHostels("75001",
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 1));

        assertThat(hotels.isLeft()).isTrue();
        assertThat(hotels.left()).isEqualTo(new SearchError("A booking must contain at least one night."));
    }
}