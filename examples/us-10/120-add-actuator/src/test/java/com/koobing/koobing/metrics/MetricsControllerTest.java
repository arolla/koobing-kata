package com.koobing.koobing.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MetricsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MeterRegistry meterRegistry;

    @Test
    public void testIncrementEndpoint() throws Exception {
        // Get the counter before calling the endpoint
        Counter myCounter = meterRegistry.find("my_counter").counter();
        double initialValue = myCounter.count();

        // Call the endpoint
        mockMvc.perform(get("/api/metrics/increment"))
                .andExpect(status().isOk())
                .andExpect(content().string("Counter incremented. Current value: " + (initialValue + 1)));

        // Verify the counter was incremented
        assertEquals(initialValue + 1, myCounter.count(), "Counter should be incremented by 1");
    }

    @Test
    public void testMultipleIncrementCalls() throws Exception {
        Counter myCounter = meterRegistry.find("my_counter").counter();
        double initialValue = myCounter.count();

        // Call the endpoint multiple times
        for (int i = 1; i <= 3; i++) {
            mockMvc.perform(get("/api/metrics/increment"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Counter incremented. Current value: " + (initialValue + i)));
        }

        // Verify final value
        assertEquals(initialValue + 3, myCounter.count(), "Counter should be incremented by 3");
    }
}