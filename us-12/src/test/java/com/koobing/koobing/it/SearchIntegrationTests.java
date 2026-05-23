package com.koobing.koobing.it;

import com.koobing.koobing.booking.BookingResult;
import com.koobing.koobing.booking.BookingService;
import com.koobing.koobing.search.SearchResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnabledIfEnvironmentVariable(named = "KOOBING_INTEGRATION_TEST", matches = "true")
@Sql(scripts = {"/sql/create-tables.sql", "/sql/insert.sql"})
@Sql(scripts = {"/sql/drop-tables.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class SearchIntegrationTests {

    @Autowired
    private TestRestTemplate client;

    @Test
    @DisplayName("Search hotels in Angers")
    void searchHostelsWithUser() {
        var resp = client.getForEntity("/api/v1/search?z=49000&d=2024-01-01&d=2024-01-02", SearchResponse.Found.class);
        assertEquals(200, resp.getStatusCode().value());

        var expectedNumberOfHostels = 2;
        assertEquals(expectedNumberOfHostels, resp.getBody().hotels().size());
    }

    @TestConfiguration
    static class SearchIntegrationTestConfiguration {
        @Bean
        public BookingService bookingService() {
            return (hotelId, roomId, arrival, departure, email) -> new BookingResult.Success("FAKE_BOOKING");
        }
    }

}
