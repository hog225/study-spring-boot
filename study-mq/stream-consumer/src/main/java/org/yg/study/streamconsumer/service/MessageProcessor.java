package org.yg.study.streamconsumer.service;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.yg.study.streamconsumer.bindings.CustomSink;
import org.yg.study.streamconsumer.event.Event;

@Log4j2
@EnableBinding(CustomSink.class)
public class MessageProcessor {

    @StreamListener(target = CustomSink.INPUT)
    public void process1(Event<Integer, String> event) {

        log.info("process1 message created at {}...", event.getEventCreatedAt());

        switch (event.getEventType()) {

            case CREATE:
                String data = event.getData();
                log.info("process1 Data Create from .... : {}", data);

                break;

            case DELETE:
                int key = event.getKey();
                log.info("process1 Data Deleted: {}", key);

                break;

            default:
                String errorMessage = "process1 Incorrect event type: " + event.getEventType() + ", expected a CREATE or DELETE event";
                log.warn(errorMessage);

        }

        log.info("process1 Message processing done!");
    }

    @StreamListener(target = CustomSink.NEW_INPUT)
    public void process2(Event<Integer, String> event) {

        log.info("process2 message created at {}...", event.getEventCreatedAt());

        switch (event.getEventType()) {

            case CREATE:
                String data = event.getData();
                log.info("process2 Data Create from .... : {}", data);

                break;

            case DELETE:
                int key = event.getKey();
                log.info("process2 Data Deleted: {}", key);

                break;

            default:
                String errorMessage = "process2 Incorrect event type: " + event.getEventType() + ", expected a CREATE or DELETE event";
                log.warn(errorMessage);

        }

        log.info("process2 Message processing done!");
    }
}
