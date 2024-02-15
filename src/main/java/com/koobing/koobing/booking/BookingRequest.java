package com.koobing.koobing.booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record BookingRequest(
        @JsonProperty("hostel_id") int hostelId,
        @JsonProperty("room_id") String roomId,
        String arrival,
        String departure,
        @Email @NotBlank String email
) {
}
