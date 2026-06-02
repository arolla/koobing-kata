package com.koobing.koobing.search.repository.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "bookings")
public class BookingEntity {
    @Id
    @Column(name = "booking_id")
    private UUID id;
    private String guestEmail;
    private LocalDate startDate;
    private LocalDate endDate;

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
}
