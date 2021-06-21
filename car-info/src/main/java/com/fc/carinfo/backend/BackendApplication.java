package com.fc.carinfo.backend;



import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
// Spring Cloud Seluth

// 이미 SpringBootApplication이 EnableAutoConfiguration 를

@SpringBootApplication(
        exclude = {
                DataSourceAutoConfiguration.class,
                DataSourceTransactionManagerAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class}
)
public class BackendApplication {
    //HttpServletRequest


    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class,
                "--spring.application.name=backend",
                "--server.port=8081");
    }

    @RestController
    @Slf4j
    static class BackendController {
        @Autowired
        BackendPaymentService paymentService;

        @GetMapping("/order/{orderNumber}")
        public String order(@PathVariable Integer orderNumber){
            log.info("controller : {}", orderNumber);
            paymentService.payment(orderNumber * 10);
            return "OK " + orderNumber;
        }
    }

    @Service
    @Slf4j    
    static class BackendPaymentService {
        // 이 서비스가 외부에 있다고 가정 할 경우 
        // 로그만으로 Controller의 로그와 Service 로그를 연관 시킬 수 없다. 
        @SneakyThrows
        //@NewSpan
        public void payment (Integer price){
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(500) + 100);
            log.info("PAYMENT APPROVED {}", price);
        }
    }

}
