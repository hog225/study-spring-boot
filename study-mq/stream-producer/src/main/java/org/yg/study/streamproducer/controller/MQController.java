package org.yg.study.streamproducer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.yg.study.streamproducer.service.MessageSender;

@RestController
@RequiredArgsConstructor
public class MQController {


    private final MessageSender msgSender;

    @GetMapping("/index/{id}")
    public String sendStreamComsumerQueue(@PathVariable("id") int id){
        msgSender.sendQueueWithExchange1(id, "sendStreamComsumerQueue");
        return "OK";
    }

    @GetMapping("/index1/{id}")
    public String sendStreamComsumerQueue2(@PathVariable("id") int id){
        msgSender.sendQueueWithExchange2(id, "sendStreamComsumerQueue2");
        return "OK";
    }
}
