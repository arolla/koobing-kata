package com.koobing.koobing.search;

import com.koobing.koobing.search.repository.JpaHotelRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SearchConfiguration {
    @Bean
    public JpaHotelRepository jpaSearchRepository() {
        return new JpaHotelRepository();
    }
}
