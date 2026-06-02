package com.koobing.koobing.search.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.jdbc.Sql;

@JdbcTest
@Sql(scripts = {"/sql/create-tables.sql", "/sql/insert.sql"})
@Sql(scripts = {"/sql/drop-tables.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class DbHotelRepositoryTests {

    @Test
    @DisplayName("search available hostels from DB")
    void availableHostels() {
    }
}
