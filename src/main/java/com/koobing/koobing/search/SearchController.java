package com.koobing.koobing.search;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SearchController {

    @GetMapping("/search")
    public SearchResponse search(@RequestParam(name = "z") String zipcode,
                                 @RequestParam(name = "d") String[] dates) {
        return new SearchResponse(
                List.of(
                        new HotelDto(1, "Elegance Hotel", "25 RUE DU LOUVRE, 75001, PARIS", 10, 150, List.of("Free Wi-Fi", "Parking", "Complimentary Breakfast")),
                        new HotelDto(2, "Charming Inn", "21 RUE DU BOULOI, 75001, PARIS", 5, 120, List.of("Free Wi-Fi", "Swimming Pool", "Room Service"))
                )
        );
    }
}
