package com.koobing.koobing.booking;

import com.koobing.koobing.booking.dto.BookingRequest;
import com.koobing.koobing.booking.dto.BookingResultDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@Validated
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/bookings")
    public ResponseEntity<BookingResultDto> bookRoom(@RequestBody @Valid BookingRequest request) {
        BookingResult result = bookingService.bookRoom(request.hostelId(),
                UUID.fromString(request.roomId()),
                LocalDate.parse(request.arrival()),
                LocalDate.parse(request.departure()),
                request.email());

        return switch (result) {
            case BookingResult.Success booking -> ResponseEntity.ok(BookingResultDto.from(booking));
            case BookingResult.Failure error ->
                    ResponseEntity.status(HttpStatus.CONFLICT).body(BookingResultDto.from(error));
        };
    }
}
