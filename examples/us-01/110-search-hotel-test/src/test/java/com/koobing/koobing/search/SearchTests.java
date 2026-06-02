package com.koobing.koobing.search;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class SearchTests {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Search hotel in Paris")
    void searchInParis() throws Exception {
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
}
