package org.yg.study.streamproducer.service;

import lombok.RequiredArgsConstructor;


import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.yg.study.streamproducer.bindings.CustomSource;
import org.yg.study.streamproducer.event.Event;

import static org.yg.study.streamproducer.event.Event.Type.CREATE;

@RequiredArgsConstructor
@Service
@EnableBinding(CustomSource.class)
public class MessageSender {

    private final CustomSource customSource;


    public String sendQueueWithExchange1(int key, String body) {
        customSource.output().send(MessageBuilder.withPayload(new Event(CREATE, key, body)).build());
        return body;
    }

    public String sendQueueWithExchange2(int key, String body) {
        customSource.newOutput().send(MessageBuilder
                .withPayload(new Event(CREATE, key, body))

                .build());
        return body;
    }
}
