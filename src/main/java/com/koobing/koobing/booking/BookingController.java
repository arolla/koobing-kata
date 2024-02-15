package com.koobing.koobing.booking;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Validated
public class BookingController {
    @PostMapping("/bookings")
    public ResponseEntity<BookingResult> bookRoom(@RequestBody @Valid BookingRequest request) {
        return ResponseEntity.ok(new BookingResult.BookingSuccess("A123"));
    }
}
