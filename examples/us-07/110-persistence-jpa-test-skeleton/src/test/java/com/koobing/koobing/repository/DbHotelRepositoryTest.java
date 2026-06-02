package com.koobing.koobing.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@Sql(scripts = {"/sql/create-tables.sql", "/sql/insert.sql"})
@Sql(scripts = {"/sql/drop-tables.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class DbHotelRepositoryTest {


    @Test
    @DisplayName("search available hotels from DB")
    void availableHostels() {
    }
}