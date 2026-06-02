package com.koobing.koobing.booking;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class BookingController {
    @PostMapping("/bookings")
    public ResponseEntity<BookingResultDto> bookRoom(@RequestBody BookingRequest request) {
        return ResponseEntity.ok(new BookingResultDto.BookingSuccess("A123"));
    }
}
