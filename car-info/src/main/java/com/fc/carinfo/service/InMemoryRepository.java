package com.fc.carinfo.service;

import java.util.concurrent.atomic.AtomicLong;

import com.fc.carinfo.domain.Order;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Repository
@Data
public class InMemoryRepository {
    final AtomicLong totalPrice = new AtomicLong();
    final AtomicLong totalCount = new AtomicLong();

    public void insertOrder(Order order){
        totalCount.addAndGet(order.getProductCount());
        totalCount.addAndGet(order.getTotalPrice());
    }
    
}
