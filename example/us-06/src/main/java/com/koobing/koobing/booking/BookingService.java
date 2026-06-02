package com.koobing.koobing.booking;

import java.time.LocalDate;
import java.util.UUID;

public interface BookingService {
    BookingResult bookRoom(int hotelId, UUID roomId, LocalDate arrival, LocalDate departure, String email);
}
