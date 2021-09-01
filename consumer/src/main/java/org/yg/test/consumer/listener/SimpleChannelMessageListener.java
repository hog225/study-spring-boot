package org.yg.test.consumer.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;
import org.yg.test.consumer.service.MartService;
import org.yg.test.consumer.service.MartServiceAsync;

@Component
public class SimpleChannelMessageListener implements ChannelAwareMessageListener {

    private final MartService martService;
    //private final MartServiceAsync martService;
    public SimpleChannelMessageListener(MartService martService){
        this.martService = martService;
    }

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        System.out.println(Thread.currentThread().getName() + ": onMessage");
        //ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.readValue(new String(message.getBody()))
        martService.accept(message);
    }
}
