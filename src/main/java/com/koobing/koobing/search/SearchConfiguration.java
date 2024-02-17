package com.koobing.koobing.search;

import com.koobing.koobing.search.repository.DbHotelRepository;
import com.koobing.koobing.search.repository.jpa.JpaHotelRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SearchConfiguration {
    @Bean
    public DbHotelRepository jpaSearchRepository(JpaHotelRepository jpaHotelRepository) {
        return new DbHotelRepository(jpaHotelRepository);
    }
}
