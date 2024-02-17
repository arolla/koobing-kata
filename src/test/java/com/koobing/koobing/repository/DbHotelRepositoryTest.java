package com.koobing.koobing.repository;

import com.koobing.koobing.search.SearchConfiguration;
import com.koobing.koobing.search.domain.Hotel;
import com.koobing.koobing.search.repository.DbHotelRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import({SearchConfiguration.class,})
@Sql(scripts = {"/sql/create-tables.sql", "/sql/insert.sql"})
@Sql(scripts = {"/sql/drop-tables.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class DbHotelRepositoryTest {

    @Autowired
    private DbHotelRepository repository;

    @Test
    @DisplayName("search available hotels from DB")
    void availableHostels() {
        List<Hotel> hotels = repository.findAvailableHotelsByZipcodeAndDates("49000", LocalDate.of(2024, 2, 5), LocalDate.of(2024, 2, 6));
        assertEquals(1, hotels.size());
        // getFirst() since Java 21
        assertEquals("Charmant Château", hotels.getFirst().name());
        assertEquals(4, hotels.getFirst().availableRooms());
        assertEquals(105, hotels.getFirst().price());
    }
}