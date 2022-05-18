package org.yg.test.producer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test/queue")
@Log4j2
public class QueueController {
    private final RabbitTemplate rabbitTemplate;

    @Value("${mq.exchange-name}")
    private String EXCHANGE_NAME;

    public QueueController(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/pub-string")
    public String testMessagePub(){
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "buy.stuff", "sugar");
        return "OK";
    }

    @GetMapping("/pub-json")
    public String testMessageJson(){
        Map<String, String> sendMap = new HashMap<>();
        sendMap.put("sugar", "3");
        sendMap.put("sult", "2");

        try{
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(sendMap);
            //json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);

            Message message = MessageBuilder
                    .withBody(json.getBytes(StandardCharsets.UTF_8))
                    .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                    .build();


            rabbitTemplate.convertAndSend(EXCHANGE_NAME, "buy.stuff.a", message);
            return "OK";

        } catch (JsonProcessingException e){
            log.info(e);
            return "NOT OK";
        }
    }

    @GetMapping("/pub-string-simple-message-queue")
    public String testMessageSimpleMessage(){
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "eat.stuff.a", "bread ~~");
        return "OK";
    }



}
