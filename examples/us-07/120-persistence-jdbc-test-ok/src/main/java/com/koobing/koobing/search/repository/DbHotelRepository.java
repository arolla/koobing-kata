package com.koobing.koobing.search.repository;

import com.koobing.koobing.search.HotelRepository;
import com.koobing.koobing.search.domain.Address;
import com.koobing.koobing.search.domain.Hotel;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class DbHotelRepository implements HotelRepository {
    private final static String QUERY = """
            with city_hotels as (select h.*, r.ROOM_ID
                                  from HOTELS h
                                           join BEDROOMS r on h.HOTEL_ID = r.HOTEL_ID
                                  where ZIPCODE = '{ZIPCODE}'),
                 booked_rooms as (select b.ROOM_ID
                                  from BOOKINGS b
                                  where ('{START_DATE}' between b.START_DATE and b.END_DATE)
                                     or '{END_DATE}' between b.START_DATE and b.END_DATE),
                 available_rooms as (select h.HOTEL_ID, r.ROOM_ID, r.PRICE
                                     from BEDROOMS r
                                              join city_hotels h on r.ROOM_ID = h.ROOM_ID
                                     where not exists (select 1
                                                       from booked_rooms b
                                                       where b.ROOM_ID = r.ROOM_ID)),
                room_count as (select HOTEL_ID, count(ROOM_ID) as COUNT from available_rooms group by HOTEL_ID),
                min_price as (select HOTEL_ID, min(price) as PRICE from available_rooms group by HOTEL_ID)
            select h.*, c.COUNT, p.PRICE
            from room_count c join min_price p on c.HOTEL_ID = p.HOTEL_ID join HOTELS h on h.HOTEL_ID = c.HOTEL_ID
            """;

    private final DataSource dataSource;

    public DbHotelRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Hotel> findAvailableHotelsByZipcodeAndDates(String zipcode, LocalDate arrivalDate, LocalDate departureDate) {
        var jdbc = new JdbcTemplate(dataSource);
        var q = QUERY.replace("{ZIPCODE}", zipcode)
                .replace("{START_DATE}", arrivalDate.toString())
                .replace("{END_DATE}", departureDate.toString());
        return jdbc.query(q,
                (rs, rowNum) -> hotelFromResultSet(rs));
    }

    private Hotel hotelFromResultSet(ResultSet rs) throws SQLException {
        return new Hotel(rs.getInt("HOTEL_ID"),
                rs.getString("NAME"),
                new Address(rs.getString("STREET"), rs.getString("CITY"), rs.getString("ZIPCODE")),
                rs.getInt("COUNT"),
                rs.getInt("PRICE"),
                Collections.emptyList());
    }
}
