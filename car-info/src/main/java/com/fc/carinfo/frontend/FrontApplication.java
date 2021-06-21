package com.fc.carinfo.frontend;

import com.fc.carinfo.backend.BackendApplication;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.concurrent.TimeUnit;

// Spring Cloud Seluth

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
public class FrontApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontApplication.class,
                "--spring.application.name=frontend",
                "--server.port=8080");
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplateBuilder().build();
    }

    @RestController
    @Slf4j
    static class FrontController{

        @Autowired RestTemplate restTemplate;
        //RestTemplate restTemplate = new RestTemplate();


        @GetMapping("/order/request")
        public String request(){
            log.info("front end ");
            String response = restTemplate.getForObject("http://localhost:8081/order/100", String.class);
            return response;
        }
    }
}
