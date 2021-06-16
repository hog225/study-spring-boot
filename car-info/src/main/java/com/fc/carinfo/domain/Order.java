package com.fc.carinfo.domain;

import java.util.Random;

import lombok.Getter;

// actuator 
@Getter
public class Order {
    static Random random = new Random();
    final int productCount;
    final int totalPrice;

    public Order(int productCount, int totalPrice){
        this.productCount = productCount;
        this.totalPrice = totalPrice;
    }

    public static Order createRandomOrder(){
        int count = random.nextInt(3) + 1;
        int price = random.nextInt(10000) * 10 + 100;

        return new Order(count, price);
    }
    
}
