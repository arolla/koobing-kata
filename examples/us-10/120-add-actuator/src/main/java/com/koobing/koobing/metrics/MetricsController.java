package com.koobing.koobing.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/metrics")
public class MetricsController {

    private final Counter myCounter;

    public MetricsController(Counter myCounter) {
        this.myCounter = myCounter;
    }

    @GetMapping("/increment")
    public String incrementCounter() {
        myCounter.increment();
        return "Counter incremented. Current value: " + myCounter.count();
    }
}