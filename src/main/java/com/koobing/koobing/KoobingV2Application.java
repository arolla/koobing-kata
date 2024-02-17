package com.koobing.koobing;

import com.koobing.koobing.booking.BookingResult;
import com.koobing.koobing.booking.BookingService;
import com.koobing.koobing.search.HotelRepository;
import com.koobing.koobing.search.SearchService;
import com.koobing.koobing.search.service.DefaultSearchService;
import com.koobing.koobing.search.service.ResilientSearchService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class KoobingV2Application {

    public static void main(String[] args) {
        SpringApplication.run(KoobingV2Application.class, args);
    }

    @Bean
    public SearchService searchService(HotelRepository hotelRepository) {
        return new ResilientSearchService(new DefaultSearchService(hotelRepository));
    }

    @Bean
    public BookingService fakeBookingService() {
        var random = new Random();
        return (hotelId, roomId, arrival, departure, email) -> new BookingResult.Success("A" + random.nextInt(100_000, 200_000));
    }
}
