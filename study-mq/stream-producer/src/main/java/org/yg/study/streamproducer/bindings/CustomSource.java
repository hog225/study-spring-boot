package org.yg.study.streamproducer.bindings;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;

public interface CustomSource extends Source {


    /**
     * Name of the output channel.
     */
    String NEW_OUTPUT = "new-output";

    /**
     * @return output channel
     */
    @Output(NEW_OUTPUT)
    MessageChannel newOutput();
}
