package org.yg.study.streamconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.yg.study.streamconsumer.channel.CustomProcessor;

@RestController
public class ProducerController {
    @Autowired
    private CustomProcessor pipe;

    @GetMapping("/produce/{id}")
    public String produce(@PathVariable int id){
        pipe.myInput()
                .send(MessageBuilder.withPayload(id)
                        .build());
        return "OK";
    }
}
