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
@Table(name = "hostels")
public class HotelEntity {
    @Id
    @Column(name = "hostel_id")
    private Integer id;

    private String name;
    private String street;
    private String zipcode;
    private String city;
    private Float rating;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "hostel_id")
    private Collection<RoomEntity> rooms = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "amenities", joinColumns = @JoinColumn(name = "hostel_id"))
    @Column(name = "amenity")
    private Set<String> amenities;

    public static Hotel toHostel(HotelEntity e, int roomCount, Integer lowestPrice) {
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
