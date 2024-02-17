package com.koobing.koobing.search;

import com.koobing.koobing.search.repository.DbHotelRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SearchConfiguration {
    @Bean
    public DbHotelRepository jpaSearchRepository() {
        return new DbHotelRepository();
    }
}
