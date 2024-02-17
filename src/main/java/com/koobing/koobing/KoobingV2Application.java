package com.koobing.koobing;

import com.koobing.koobing.search.HotelRepository;
import com.koobing.koobing.search.SearchService;
import com.koobing.koobing.search.service.DefaultSearchService;
import com.koobing.koobing.search.service.ResilientSearchService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KoobingV2Application {

    public static void main(String[] args) {
        SpringApplication.run(KoobingV2Application.class, args);
    }

    @Bean
    public SearchService searchService(HotelRepository hotelRepository) {
        return new ResilientSearchService(new DefaultSearchService(hotelRepository));
    }
}
