package com.koobing.koobing.booking;

public sealed interface BookingResult {
    record Success(String bookingNumber) implements BookingResult {
    }

    record Failure(String message, BookingError error) implements BookingResult {
    }
}
