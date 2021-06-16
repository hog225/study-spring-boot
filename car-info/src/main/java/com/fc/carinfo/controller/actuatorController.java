package com.fc.carinfo.controller;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.fc.carinfo.service.RequestOrderUsecase;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActuatorController {

    private final Random random = new Random();
    private final RequestOrderUsecase requestOrderUsecase;

    public ActuatorController(RequestOrderUsecase requestOrderUsecase){
        this.requestOrderUsecase = requestOrderUsecase;
    }

    @GetMapping("/hello")
    public String world(){
        return "hello world";
    }

    //Random 하게 500 에러를 발생 시킴
    @GetMapping("/hello/{percent}")
    public String world2(@PathVariable Integer percent) throws InterruptedException{
        if (random.nextInt(100) < percent){
            int sleepTime = 100 * random.nextInt(500);
            System.out.println(sleepTime);
            TimeUnit.MILLISECONDS.sleep(sleepTime/1000);
            throw new RuntimeException("delayed");
        }
        return "world";
        
    }

    //Random 하게 500 에러를 발생 시킴
    @GetMapping("/order")
    public void order() throws InterruptedException{
        requestOrderUsecase.requestOrder();
    }
}
