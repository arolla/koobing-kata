package com.koobing.koobing;

import com.koobing.koobing.booking.BookingResult;
import com.koobing.koobing.booking.BookingService;
import com.koobing.koobing.search.HotelRepository;
import com.koobing.koobing.search.SearchService;
import com.koobing.koobing.search.repository.ResilientHotelRepository;
import com.koobing.koobing.search.service.DefaultSearchService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class KoobingConfiguration {
    @Bean
    public SearchService searchService(HotelRepository hotelRepository) {
        return new DefaultSearchService(new ResilientHotelRepository(hotelRepository));
    }

    @Bean
    public BookingService fakeBookingService() {
        var random = new Random();
        return (hotelId, roomId, arrival, departure, email) -> new BookingResult.Success("A" + random.nextInt(100_000, 200_000));
    }
}
