package com.koobing.koobing.booking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.koobing.koobing.booking.BookingResult;

public sealed interface BookingResultDto {
    static BookingResultDto from(BookingResult.Failure failure) {
        return new BookingFailure(failure.error().message(), failure.error().code());
    }

    static BookingResultDto from(BookingResult.Success booking) {
        return new BookingSuccess(booking.bookingNumber());
    }

    record BookingSuccess(@JsonProperty("booking_number") String bookingNumber) implements BookingResultDto {
    }

    record BookingFailure(String message, @JsonProperty("error_code") String errorCode) implements BookingResultDto {
    }
}
