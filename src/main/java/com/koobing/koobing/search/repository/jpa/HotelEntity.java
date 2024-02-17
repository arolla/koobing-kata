package com.koobing.koobing.search.repository.jpa;

import com.koobing.koobing.search.domain.Address;
import com.koobing.koobing.search.domain.Hotel;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "hotels")
public class HotelEntity {
    @Id
    @Column(name = "hotel_id")
    private Integer id;

    private String name;
    private String street;
    private String zipcode;
    private String city;
    private Float rating;

    @OneToMany
    @JoinColumn(name = "hotel_id")
    private Collection<RoomEntity> rooms = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "amenities", joinColumns = @JoinColumn(name = "hotel_id"))
    @Column(name = "amenity")
    private Set<String> amenities;

    public static Hotel toHotel(HotelEntity e, int roomCount, Integer lowestPrice) {
        return new Hotel(
                e.id,
                e.name,
                new Address(e.street, e.zipcode, e.city),
                roomCount,
                lowestPrice,
                new ArrayList<>(e.amenities));
    }

    public List<RoomEntity> availableRooms(LocalDate arrivalDate, LocalDate departureDate) {
        return rooms.stream()
                .filter(roomEntity -> roomEntity.isAvailable(arrivalDate, departureDate))
                .toList();
    }
}
