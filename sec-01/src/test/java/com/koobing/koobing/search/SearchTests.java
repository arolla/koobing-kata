package com.koobing.koobing.search;

import com.koobing.koobing.search.domain.Address;
import com.koobing.koobing.search.domain.Hotel;
import com.koobing.koobing.security.SecurityConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({SearchController.class, SecurityConfiguration.class})
public class SearchTests {

    @MockBean
    private SearchService searchService;

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser
    @DisplayName("Search hotel in Paris")
    void searchInParis() throws Exception {
        given(searchService.availableHostels(anyString(), any(LocalDate.class), any(LocalDate.class)))
                .willReturn(
                        List.of(
                                new Hotel(1, "Elegance Hotel", new Address("25 RUE DU LOUVRE", "PARIS", "75001"), 10, 150, List.of("Free Wi-Fi", "Parking", "Complimentary Breakfast")),
                                new Hotel(2, "Charming Inn", new Address("21 RUE DU BOULOI", "PARIS", "75001"), 5, 120, List.of("Free Wi-Fi", "Swimming Pool", "Room Service"))
                        )
                );

        var expectedJson = """
                {
                  "hotels": [
                    {
                      "id": 1,
                      "name": "Elegance Hotel",
                      "address": "25 RUE DU LOUVRE, 75001, PARIS",
                      "available_rooms": 10,
                      "price": 150,
                      "amenities": ["Free Wi-Fi", "Parking", "Complimentary Breakfast"]
                    },
                    {
                      "id": 2,
                      "name": "Charming Inn",
                      "address": "21 RUE DU BOULOI, 75001, PARIS",
                      "available_rooms": 5,
                      "price": 120,
                      "amenities": ["Free Wi-Fi", "Swimming Pool", "Room Service"]
                    }
                  ]
                }
                                """;

        mvc.perform(get("/api/v1/search?z=75001&d=2024-01-01&d=2024-01-02"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

    }

    @Test
    @WithMockUser
    @DisplayName("Search hotel in Paris. None is available.")
    void noHotelFound() throws Exception {
        given(searchService.availableHostels(anyString(), any(LocalDate.class), any(LocalDate.class)))
                .willReturn(Collections.emptyList());

        var expectedJson = """
                {
                  "search_criteria": {
                    "zipcode": "75001",
                    "arrival_date": "2024-01-03",
                    "departure_date": "2024-01-04"
                  }
                }
                                """;

        mvc.perform(get("/api/v1/search?z=75001&d=2024-01-03&d=2024-01-04"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().json(expectedJson));

    }

    @ParameterizedTest
    @WithMockUser
    @ValueSource(strings = {
            "/api/v1/search?z=75001&d=2024-01-01",
            "/api/v1/search?z=75001&d=2024-01-01&d=2024-01-02&d=2024-01-03"
    })
    @DisplayName("Search hotel in Paris with invalid number of dates.")
    void searchInParisWithOneMissingDate(String uri) throws Exception {
        var expectedJson = """
                {
                    "message": "Two dates must be provided when searching hotels."
                }
                """;

        mvc.perform(get(uri))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedJson));
    }

    @Test
    @WithAnonymousUser
    @DisplayName("An anonymous user try searching hotel in Paris")
    void searchInParisWithAnonymousUser() throws Exception {
        given(searchService.availableHostels(anyString(), any(LocalDate.class), any(LocalDate.class)))
                .willReturn(
                        List.of(
                                new Hotel(1, "Elegance Hotel", new Address("25 RUE DU LOUVRE", "PARIS", "75001"), 10, 150, List.of("Free Wi-Fi", "Parking", "Complimentary Breakfast")),
                                new Hotel(2, "Charming Inn", new Address("21 RUE DU BOULOI", "PARIS", "75001"), 5, 120, List.of("Free Wi-Fi", "Swimming Pool", "Room Service"))
                        )
                );

        mvc.perform(get("/api/v1/search?z=75001&d=2024-01-01&d=2024-01-02"))
                .andDo(print())
                .andExpect(status().isUnauthorized());

    }
}
