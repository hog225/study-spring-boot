package com.fc.carinfo.study;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

@DisplayName("Count Meter Test")
public class CounterTest {

    
    private MeterRegistry meterRegistry;

    @BeforeEach
    void init(){
        meterRegistry = new SimpleMeterRegistry();
    }

    

    @Test
    @DisplayName("Counter Increament")
    void testCounterMeter(){
        Counter counter = Counter.builder("counter.test")
            .register(meterRegistry);

        counter.increment(3.0);
        assertAll(
            () -> assertEquals(counter.count(), 3.0),
            () -> assertEquals(counter.getId().getName(), "counter.test")
        );

    }
    
}
