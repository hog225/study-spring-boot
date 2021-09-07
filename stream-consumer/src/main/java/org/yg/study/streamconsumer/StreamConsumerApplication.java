package org.yg.study.streamconsumer;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;

import org.springframework.context.annotation.Bean;
import org.springframework.core.log.LogMessage;
import org.springframework.messaging.Message;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;



@SpringBootApplication
//@EnableBinding(CustomProcessor.class)
@Log4j2
public class StreamConsumerApplication {

//	@Autowired
//	private CustomProcessor processor;

	public static void main(String[] args) {
		SpringApplication.run(StreamConsumerApplication.class, args);
	}

//	@StreamListener(Processor.INPUT)
//	@SendTo(Processor.OUTPUT)
//	public String enrichLogMessage(String logs) {
//		return String.format("[1] : %s", logs);
//	}


//	@StreamListener(CustomProcessor.INPUT)
//	public void routeValues(Integer val) {
//		if (val < 10) {
//			processor.anOutput().send(message(val));
//		} else {
//			processor.anotherOutput().send(message(val));
//		}
//	}
//
//	private static final <T> Message<T> message(T val) {
//		return MessageBuilder.withPayload(val).build();
//	}
//
//
//	@StreamListener(
//			target = CustomProcessor.INPUT,
//			condition = "payload < 10")
//	public void routeValuesToAnOutput(Integer val) {
//		System.out.printf("routeValuesToAnOutput %d \n", val);
//		processor.anOutput().send(message(val));
//	}
//
//	@StreamListener(
//			target = CustomProcessor.INPUT,
//			condition = "payload >= 10")
//	public void routeValuesToAnotherOutput(Integer val) {
//		System.out.printf("routeValuesToAnotherOutput %d \n", val);
//
//		processor.anotherOutput().send(message(val));
//	}

//	@Bean
//	public MessageConverter providesTextPlainMessageConverter() {
//		return new TextPlainMessageConverter();
//	}
}
