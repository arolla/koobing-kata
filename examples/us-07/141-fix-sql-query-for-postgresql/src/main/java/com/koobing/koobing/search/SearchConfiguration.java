package com.koobing.koobing.search;

import com.koobing.koobing.search.repository.DbHotelRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SearchConfiguration {
    @Bean
    public DbHotelRepository searchService(DataSource dataSource) {
        return new DbHotelRepository(dataSource);
    }
}
