package com.koobing.koobing.search.service;

import com.koobing.koobing.logs.Context;
import com.koobing.koobing.search.SearchError;
import com.koobing.koobing.search.SearchService;
import com.koobing.koobing.search.domain.AvailableHotels;
import com.koobing.koobing.search.domain.Zipcode;
import com.koobing.koobing.utils.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.time.LocalDate;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ResilientSearchService implements SearchService {
    private static final Logger log = LoggerFactory.getLogger(ResilientSearchService.class);
    private final SearchService delegate;

    public ResilientSearchService(SearchService delegate) {
        this.delegate = delegate;
    }

    @Override
    public Either<SearchError, AvailableHotels> availableHostels(Zipcode zipcode, LocalDate arrivalDate, LocalDate departureDate) {
        try {
            // REMARK: Don't forget CompletableFuture are launched from other thread.
            var correlationId = Context.correlationId();
            CompletableFuture<Either<SearchError, AvailableHotels>> result = CompletableFuture.supplyAsync(() -> {
                MDC.put("correlationId", correlationId.toString());
                return delegate.availableHostels(zipcode, arrivalDate, departureDate);
            });
            return result.get(500, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            log.warn(e.getMessage(), e);
            return Either.right(new AvailableHotels(Collections.emptyList()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Either.right(new AvailableHotels(Collections.emptyList()));
        }
    }
}
