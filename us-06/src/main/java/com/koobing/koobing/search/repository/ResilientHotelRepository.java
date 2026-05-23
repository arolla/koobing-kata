package com.koobing.koobing.search.repository;

import com.koobing.koobing.search.HotelRepository;
import com.koobing.koobing.search.domain.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class ResilientHotelRepository implements HotelRepository {
    private static final Logger log = LoggerFactory.getLogger(ResilientHotelRepository.class);
    private final HotelRepository delegate;

    public ResilientHotelRepository(HotelRepository delegate) {
        this.delegate = delegate;
    }

    @Override
    public List<Hotel> findAvailableHotelsByZipcodeAndDates(String zipcode, LocalDate arrivalDate, LocalDate departureDate) {
        try {
            CompletableFuture<List<Hotel>> result = CompletableFuture.supplyAsync(() -> delegate.findAvailableHotelsByZipcodeAndDates(zipcode, arrivalDate, departureDate));
            return result.get(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            log.warn(e.getMessage(), e);
            return Collections.emptyList();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
