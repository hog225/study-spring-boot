package org.yg.study.streamconsumer.bindings;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.SubscribableChannel;

public interface CustomSink extends Sink {

    String NEW_INPUT = "new-input";

    @Input(NEW_INPUT)
    SubscribableChannel newInput();

}
