package com.koobing.koobing;

import com.koobing.koobing.search.HotelRepository;
import com.koobing.koobing.search.SearchService;
import com.koobing.koobing.search.service.DefaultSearchService;
import com.koobing.koobing.search.service.ResilientSearchService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KoobingConfiguration {
    @Bean
    public SearchService searchService(HotelRepository hotelRepository) {
        return new ResilientSearchService(new DefaultSearchService(hotelRepository));
    }
}
