package com.koobing.koobing.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomMetricsConfigurationTest {

    @Autowired
    private MeterRegistry meterRegistry;

    @Test
    public void testMyCounterMetricIsRegistered() {
        // Verify the counter is registered
        Counter myCounter = meterRegistry.find("my_counter").counter();
        assertNotNull(myCounter, "my_counter should be registered in the meter registry");
        
        // Verify initial value is 0
        assertEquals(0.0, myCounter.count(), "Initial counter value should be 0");
        
        // Increment the counter
        myCounter.increment();
        
        // Verify the counter was incremented
        assertEquals(1.0, myCounter.count(), "Counter should be incremented to 1");
    }
}