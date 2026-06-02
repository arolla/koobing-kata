package com.koobing.koobing.search.repository;

import com.koobing.koobing.search.HotelRepository;
import com.koobing.koobing.search.domain.Hotel;
import com.koobing.koobing.search.repository.jpa.HotelEntity;
import com.koobing.koobing.search.repository.jpa.JpaHotelRepository;
import com.koobing.koobing.search.repository.jpa.RoomEntity;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class DbHotelRepository implements HotelRepository {
    private final JpaHotelRepository jpaHotelRepository;

    public DbHotelRepository(JpaHotelRepository jpaHotelRepository) {
        this.jpaHotelRepository = jpaHotelRepository;
    }

    @Override
    public List<Hotel> findAvailableHotelsByZipcodeAndDates(String zipcode, LocalDate arrivalDate, LocalDate departureDate) {
        // all hostels in angers
        // -> find hostels in angers with at least one available room

        var hostels = jpaHotelRepository.findByZipcode(zipcode);

        List<HotelEntity> availableHostels = hostels.stream()
                .filter(entity -> !entity.availableRooms(arrivalDate, departureDate).isEmpty())
                .toList();

        return availableHostels.stream()
                .map(hostelEntity -> {
                    var rooms = hostelEntity.availableRooms(arrivalDate, departureDate);
                    RoomEntity firstRoom = rooms.stream()
                            .min(Comparator.comparing(RoomEntity::getPrice))
                            .orElseThrow();
                    return HotelEntity.toHotel(hostelEntity, rooms.size(), firstRoom.getPrice());
                })
                .toList();
    }
}
