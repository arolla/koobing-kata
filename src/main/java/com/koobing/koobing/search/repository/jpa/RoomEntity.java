package com.koobing.koobing.search.repository.jpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "bedrooms")
public class RoomEntity {
    @Id
    @Column(name = "room_id")
    private UUID id;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Collection<BookingEntity> bookings = new ArrayList<>();

    private Integer price;

}
