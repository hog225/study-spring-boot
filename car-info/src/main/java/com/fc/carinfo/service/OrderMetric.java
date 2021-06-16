package com.fc.carinfo.service;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.FunctionCounter;
import io.micrometer.core.instrument.MeterRegistry;

@Component
public class OrderMetric {

    InMemoryRepository inMemoryRepository;

    public OrderMetric(MeterRegistry meterRegistry, InMemoryRepository repository){
        FunctionCounter.builder("product.count", repository, c -> c.getTotalCount().doubleValue())
            .description("sell count")
            .register(meterRegistry);

        FunctionCounter.builder("order.price", repository, c -> c.getTotalPrice().doubleValue())
            .description("price of invoice")
            .register(meterRegistry);
        
    }
}