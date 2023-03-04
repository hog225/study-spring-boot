package com.yghong.springintegrationtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.context.IntegrationFlowContext;
import org.springframework.integration.ip.tcp.TcpReceivingChannelAdapter;
import org.springframework.integration.ip.tcp.TcpSendingMessageHandler;
import org.springframework.integration.ip.tcp.connection.TcpNetClientConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
import org.springframework.integration.router.AbstractMessageRouter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@EnableIntegration
@IntegrationComponentScan
@Configuration
public class DynamicTcpConfig {

    // Client side

    @MessagingGateway(defaultRequestChannel = "toTcp.input")
    public interface ToTCP {

        public void send(String data, @Header("host") String host, @Header("port") int port);

    }

    @Bean
    public IntegrationFlow toTcp() {
        return f -> f.route(new TcpRouter());
    }

    // Two servers

    @Bean
    public TcpNetServerConnectionFactory cfOne() {
        return new TcpNetServerConnectionFactory(1234);
    }

    @Bean
    public TcpReceivingChannelAdapter inOne(TcpNetServerConnectionFactory cfOne) {
        TcpReceivingChannelAdapter adapter = new TcpReceivingChannelAdapter();
        adapter.setConnectionFactory(cfOne);
        adapter.setOutputChannel(outputChannel());
        return adapter;
    }

    @Bean
    public TcpNetServerConnectionFactory cfTwo() {
        return new TcpNetServerConnectionFactory(5678);
    }

    @Bean
    public TcpReceivingChannelAdapter inTwo(TcpNetServerConnectionFactory cfTwo) {
        TcpReceivingChannelAdapter adapter = new TcpReceivingChannelAdapter();
        adapter.setConnectionFactory(cfTwo);
        adapter.setOutputChannel(outputChannel());
        return adapter;
    }

    @Bean
    public QueueChannel outputChannel() {
        return new QueueChannel();
    }

    public static class TcpRouter extends AbstractMessageRouter {

        private final static int MAX_CACHED = 10; // When this is exceeded, we remove the LRU.

        @SuppressWarnings("serial")
        private final LinkedHashMap<String, MessageChannel> subFlows =
                new LinkedHashMap<String, MessageChannel>(MAX_CACHED, .75f, true) {

                    @Override
                    protected boolean removeEldestEntry(Map.Entry<String, MessageChannel> eldest) {
                        if (size() > MAX_CACHED) {
                            removeSubFlow(eldest);
                            return true;
                        }
                        else {
                            return false;
                        }
                    }

                };

        @Autowired
        private IntegrationFlowContext flowContext;

        @Override
        protected synchronized Collection<MessageChannel> determineTargetChannels(Message<?> message) {
            MessageChannel channel = this.subFlows
                    .get(message.getHeaders().get("host", String.class) + message.getHeaders().get("port"));
            if (channel == null) {
                channel = createNewSubflow(message);
            }
            return Collections.singletonList(channel);
        }

        private MessageChannel createNewSubflow(Message<?> message) {
            String host = (String) message.getHeaders().get("host");
            Integer port = (Integer) message.getHeaders().get("port");
            Assert.state(host != null && port != null, "host and/or port header missing");
            String hostPort = host + port;

            TcpNetClientConnectionFactory cf = new TcpNetClientConnectionFactory(host, port);
            TcpSendingMessageHandler handler = new TcpSendingMessageHandler();
            handler.setConnectionFactory(cf);
            IntegrationFlow flow = f -> f.handle(handler);
            IntegrationFlowContext.IntegrationFlowRegistration flowRegistration =
                    this.flowContext.registration(flow)
                            .addBean(cf)
                            .id(hostPort + ".flow")
                            .register();
            MessageChannel inputChannel = flowRegistration.getInputChannel();
            this.subFlows.put(hostPort, inputChannel);
            return inputChannel;
        }

        private void removeSubFlow(Map.Entry<String, MessageChannel> eldest) {
            String hostPort = eldest.getKey();
            this.flowContext.remove(hostPort + ".flow");
        }

    }
}
