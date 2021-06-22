package com.fc.carinfo.backend;



import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.cloud.sleuth.annotation.SpanTag;
import org.springframework.cloud.sleuth.instrument.async.TraceableExecutorService;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
// Spring Cloud Seluth

// 이미 SpringBootApplication이 EnableAutoConfiguration 를
@EnableKafka
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
        //BackendPaymentService paymentService;
        BackendKafkaPaymentService paymentService;

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
        // @NewSpan은 Tracer를 주입받아서 할 수도 있다.
        private ExecutorService executorService;

        public BackendPaymentService(BeanFactory beanFactory){
            this.executorService = new TraceableExecutorService(
                    beanFactory, Executors.newFixedThreadPool(10), "lazy-pool"
            );
        }


        @SneakyThrows
        @NewSpan(name= "backendPayment")
        public void payment (@SpanTag("payment-price") Integer price){
            executorService.submit(new Runnable() {
                @SneakyThrows
                @Override
                @NewSpan("runnable")
                public void run() {
                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(500) + 100);
                    log.info("PAYMENT APPROVED {}", price);
                }
            });
            log.info("APPROVED");

        }
    }

    @Service
    @Slf4j
    static class BackendKafkaPaymentService {

        @Autowired
        KafkaTemplate<String, String> kafkaTemplate;


        @NewSpan(name= "backendPayment")
        public void payment (Integer price){
            kafkaTemplate.send("backend", price + "CONFIRMED !!!!!!");
            log.info("APPROVED");
        }

        @KafkaListener(topics = "backend", groupId = "backedn-c1")
        public void consume(ConsumerRecord<String, String> record){
            log.info("consume: {} 결제 완료", record.value());
        }
    }

}
