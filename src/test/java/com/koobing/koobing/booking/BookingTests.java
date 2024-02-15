package com.koobing.koobing.booking;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({BookingController.class})
public class BookingTests {
    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Book a room")
    void bookRoom() throws Exception {
        var bookingJson = """
                {
                    "hostel_id": 1,
                    "room_id": "46da9f48-ea47-4d9d-9f4b-52b5e56f4e2e",
                    "arrival": "2020-01-01",
                    "departure": "2020-01-02",
                    "email" : "foo.bar@example.com"
                }
                """;

        var expectedJson = """
                {
                    "booking_number": "A123"
                }
                """;


        mvc.perform(post("/api/v1/bookings")
                        .contentType("application/json")
                        .content(bookingJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

    }

    @Test
    @DisplayName("Try booking a room without email")
    void bookRoomWithoutEmail() throws Exception {
        var bookingJson = """
                {
                    "hostel_id": 1,
                    "room_id": "46da9f48-ea47-4d9d-9f4b-52b5e56f4e2e",
                    "arrival": "2020-01-01",
                    "departure": "2020-01-02"
                }
                """;

        var expectedJson = """
                {
                    "message": "Invalid email provided."
                }
                """;


        mvc.perform(post("/api/v1/bookings")
                        .contentType("application/json")
                        .content(bookingJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedJson));

    }
}
