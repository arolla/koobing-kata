package com.koobing.koobing.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomMetricsConfiguration {

    @Bean
    public Counter myCounter(MeterRegistry meterRegistry) {
        return Counter.builder("my_counter")
                .description("A custom counter metric")
                .register(meterRegistry);
    }
}