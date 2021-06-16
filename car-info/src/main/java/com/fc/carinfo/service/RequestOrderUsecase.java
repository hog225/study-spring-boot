package com.fc.carinfo.service;

import com.fc.carinfo.domain.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
//actuator
@Service
public class RequestOrderUsecase {
    InMemoryRepository repository;
    //private final Counter counter;

    public RequestOrderUsecase(InMemoryRepository repository, MeterRegistry meterRegistry){
        this.repository = repository;
        // counter = Counter.builder("order.count")
        //     .tag("app", "order")
        //     .register(meterRegistry);
    }


    // 실제 요 메서드가 몇번 호출 되었나 ? 
    // Counter, FunctionCount
    public void requestOrder(){
        //increment
        Order order = Order.createRandomOrder();
        repository.insertOrder(order);
        // counter.increment();
    }
    
}
