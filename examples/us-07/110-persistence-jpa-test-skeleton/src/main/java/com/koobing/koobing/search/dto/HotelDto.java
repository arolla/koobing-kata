package com.koobing.koobing.search.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.koobing.koobing.search.domain.Hotel;

import java.util.List;

public record HotelDto(
        int id,
        String name,
        String address,
        @JsonProperty("available_rooms")
        int availableRooms,
        int price,
        List<String> amenities
) {
        public static HotelDto from(Hotel hotel) {
                return new HotelDto(
                        hotel.id(),
                        hotel.name(),
                        String.format("%s, %s, %s", hotel.address().street(), hotel.address().zipcode(), hotel.address().city()),
                        hotel.availableRooms(),
                        hotel.price(),
                        hotel.amenities());
        }
}

