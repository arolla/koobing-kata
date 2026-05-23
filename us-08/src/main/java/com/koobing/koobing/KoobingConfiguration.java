package com.koobing.koobing;

import com.koobing.koobing.search.HotelRepository;
import com.koobing.koobing.search.SearchService;
import com.koobing.koobing.search.repository.ResilientHotelRepository;
import com.koobing.koobing.search.service.DefaultSearchService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KoobingConfiguration {
    @Bean
    public SearchService searchService(HotelRepository hotelRepository) {
        return new DefaultSearchService(new ResilientHotelRepository(hotelRepository));
    }
}
