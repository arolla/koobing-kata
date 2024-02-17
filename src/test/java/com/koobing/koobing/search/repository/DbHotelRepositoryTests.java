package com.koobing.koobing.search.repository;

import com.koobing.koobing.search.SearchConfiguration;
import com.koobing.koobing.search.domain.Hotel;
import com.koobing.koobing.search.domain.Zipcode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@TestPropertySource("/application-postgres.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({SearchConfiguration.class})
@Sql(scripts = {"/sql/create-tables.sql", "/sql/insert.sql"})
@Sql(scripts = {"/sql/drop-tables.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class DbHotelRepositoryTests {
    @Autowired
    private DbHotelRepository repository;

    @Test
    @DisplayName("search available hostels from DB")
    void availableHostels() {
        List<Hotel> hostels = repository.findAvailableHotelsByZipcodeAndDates(new Zipcode("49000"), LocalDate.of(2024, 2, 5), LocalDate.of(2024, 2, 6));
        assertEquals(1, hostels.size());
        // getFirst() since Java 21
        assertEquals("Charmant Château", hostels.getFirst().name());
        assertEquals(4, hostels.getFirst().availableRooms());
        assertEquals(105, hostels.getFirst().price());
    }
}
