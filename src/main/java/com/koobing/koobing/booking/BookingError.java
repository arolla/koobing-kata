package com.koobing.koobing.booking;

public enum BookingError {
    ALREADY_BOOKED("Room already booked.", "ALREADY_BOOKED");

    private final String message;
    private final String code;

    BookingError(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String message() {
        return message;
    }

    public String code() {
        return code;
    }
}
