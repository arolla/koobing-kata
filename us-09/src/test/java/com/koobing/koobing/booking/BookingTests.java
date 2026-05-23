package com.koobing.koobing.booking;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({BookingController.class})
public class BookingTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookingService bookingService;

    @Test
    @DisplayName("Book a room")
    void bookRoom() throws Exception {
        given(bookingService.bookRoom(anyInt(), any(UUID.class), any(LocalDate.class), any(LocalDate.class), anyString()))
                .willReturn(new BookingResult.Success("A123"));

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

    @ParameterizedTest
    @ValueSource(strings = {
            """
                    {
                        "hostel_id": 1,
                        "room_id": "46da9f48-ea47-4d9d-9f4b-52b5e56f4e2e",
                        "arrival": "2020-01-01",
                        "departure": "2020-01-02"
                    }
                        """,
            """
                    {
                        "hostel_id": 1,
                        "room_id": "46da9f48-ea47-4d9d-9f4b-52b5e56f4e2e",
                        "arrival": "2020-01-01",
                        "departure": "2020-01-02",
                        "email" : "foo.bar"
                    }
                        """
    })
    @DisplayName("Try booking a room without email")
    void bookRoomWithoutEmail(String bookingJson) throws Exception {
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

    @Test
    @DisplayName("Book a room already booked")
    void bookRoomTwice() throws Exception {
        given(bookingService.bookRoom(anyInt(), any(UUID.class), any(LocalDate.class), any(LocalDate.class), anyString()))
                .willReturn(new BookingResult.Failure("Room already booked.", BookingError.ALREADY_BOOKED));

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
                    "message": "Room already booked.",
                    "error_code": "ALREADY_BOOKED"
                }
                """;


        mvc.perform(post("/api/v1/bookings")
                        .contentType("application/json")
                        .content(bookingJson))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(content().json(expectedJson));

    }

}
