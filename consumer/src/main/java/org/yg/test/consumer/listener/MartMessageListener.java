package org.yg.test.consumer.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MartMessageListener {


    //Listener Queue
    @RabbitListener(queues = {"cashier.queue", "tasting.queue"})
    public void receiveMessage(final Message message) {
        StringBuilder msgBody = new StringBuilder();
        System.out.println("message = " + message);
        for (byte a : message.getBody()){
            msgBody.append((char) a);
        }
        System.out.println(msgBody);
    }


}
