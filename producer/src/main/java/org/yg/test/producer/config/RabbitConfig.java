package org.yg.test.producer.config;



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



@Configuration
public class RabbitConfig {



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



}
