package com.koobing.koobing.search;

import com.koobing.koobing.search.domain.AvailableHotels;
import com.koobing.koobing.search.dto.HotelDto;
import com.koobing.koobing.search.dto.SearchCriteriaDto;
import com.koobing.koobing.utils.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public ResponseEntity<SearchResponse> search(@RequestParam(name = "z") String zipcode,
                                                 @RequestParam(name = "d") String[] dates) {

        if (dates.length != 2) {
            return ResponseEntity.badRequest().body(new SearchResponse.Failure("Two dates must be provided when searching hotels."));
        }
        var arrivalDate = LocalDate.parse(dates[0]);
        var departureDate = LocalDate.parse(dates[1]);

        Either<SearchError, AvailableHotels> availableHostels = searchService.availableHostels(zipcode, arrivalDate, departureDate);

        if (availableHostels.right().isEmpty()) {
            return new ResponseEntity<>(
                    new SearchResponse.NotFound(
                            new SearchCriteriaDto(zipcode, dates[0], dates[1])
                    ),
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(new SearchResponse.Found(
                // transformation from domain to dto
                availableHostels.right()
                        .hotels()
                        .stream()
                        .map(HotelDto::from).toList()
        ));

    }
}
