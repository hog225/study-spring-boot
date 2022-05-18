package org.yg.study.streamconsumer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.support.MessageBuilder;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StreamConsumerApplicationTests {

	@Autowired
	private Processor pipe;

	@Autowired
	private MessageCollector messageCollector;



	@Test
	public void whenSendMessage_thenResponseShouldUpdateText() {
		pipe.input()
				.send(MessageBuilder.withPayload("This is my message")
						.build());

		Object payload = messageCollector.forChannel(pipe.output())
				.poll()
				.getPayload();


		System.out.println(payload.toString());
		assertThat(payload.toString()).isEqualTo("[1] : This is my message");
	}
}
