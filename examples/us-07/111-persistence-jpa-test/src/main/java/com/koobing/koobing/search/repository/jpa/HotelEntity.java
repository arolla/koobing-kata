package com.koobing.koobing.search.repository.jpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id")
    private Collection<RoomEntity> rooms = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "amenities", joinColumns = @JoinColumn(name = "hotel_id"))
    @Column(name = "amenity")
    private Set<String> amenities;
}
