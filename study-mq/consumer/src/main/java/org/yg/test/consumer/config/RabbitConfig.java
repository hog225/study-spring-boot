package org.yg.test.consumer.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.yg.test.consumer.listener.SimpleChannelMessageListener;

@Configuration
public class RabbitConfig {

    private final SimpleChannelMessageListener simpleChannelMessageListener;

    @Value("${mq.exchange-name}")
    private String EXCHANGE_NAME;

    @Value("${mq.queue-name}")
    private String QUEUE_NAME;

    @Value("${mq.queue-name1}")
    private String QUEUE_NAME1;

    @Value("${mq.routing-key}")
    private String ROUTING_KEY;

    @Value("${mq.routing-key1}")
    private String ROUTING_KEY1;


    public RabbitConfig(SimpleChannelMessageListener simpleChannelMessageListener) {
        this.simpleChannelMessageListener = simpleChannelMessageListener;
    }

    @Bean
    Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    Queue queue1() {
        return new Queue(QUEUE_NAME1, false);
    }


    @Bean
    TopicExchange exchange(){
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    Binding binding(@Qualifier("queue") Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    Binding binding1(@Qualifier("queue1") Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY1);
    }

    // listener를 세팅
    @Bean("simpleMessageListenerContainer")
    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory conn){
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(conn);
        simpleMessageListenerContainer.setQueueNames(QUEUE_NAME1);
        simpleMessageListenerContainer.setConcurrentConsumers(10); // queue 를 처리하는 thread
        simpleMessageListenerContainer.setMessageListener(simpleChannelMessageListener);
        return simpleMessageListenerContainer;
    }


}
