package com.fc.carinfo.backend;



import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
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
        @GetMapping("/order/{orderNumber}")
        public String order(@PathVariable Integer orderNumber){
            log.info("controller : {}", orderNumber);
            return "OK " + orderNumber;
        }
    }

}
