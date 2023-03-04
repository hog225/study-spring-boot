package com.yghong.springintegrationtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.ip.tcp.TcpInboundGateway;
import org.springframework.integration.ip.tcp.TcpOutboundGateway;
import org.springframework.integration.ip.tcp.connection.AbstractClientConnectionFactory;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNetClientConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
import org.springframework.messaging.MessageChannel;

@SpringBootApplication
public class SpringIntegrationTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringIntegrationTestApplication.class, args);

    }
//
//    @Bean
//    public AbstractServerConnectionFactory serverConnectionFactory() {
//        return new TcpNetServerConnectionFactory(1234);
//    }
//
//    @Bean
//    public AbstractClientConnectionFactory clientConnectionFactory() {
//        return new TcpNetClientConnectionFactory("172.16.48.131", 80);
//    }
//
//    @Bean
//    public TcpInboundGateway tcpInboundGateway(AbstractServerConnectionFactory serverConnectionFactory, MessageChannel inputChannel,
//                                               MessageChannel errorChannel) {
//        TcpInboundGateway gateway = new TcpInboundGateway();
//        gateway.setConnectionFactory(serverConnectionFactory);
//        gateway.setRequestChannel(inputChannel);
//        gateway.setErrorChannel(errorChannel);
//        gateway.setReplyTimeout(5000);
//        return gateway;
//    }
//
//    @Bean
//    public TcpOutboundGateway tcpOutboundGateway(AbstractClientConnectionFactory clientConnectionFactory, MessageChannel outputChannel,
//                                                 MessageChannel replyChannel) {
//        TcpOutboundGateway gateway = new TcpOutboundGateway();
//        gateway.setConnectionFactory(clientConnectionFactory);
//        gateway.setOutputChannel(outputChannel);
//        gateway.setReplyChannel(replyChannel);
//
//        return gateway;
//    }
//
//    @Bean
//    public MessageChannel inputChannel() {
//        return new DirectChannel();
//    }
//
//    @Bean
//    public MessageChannel outputChannel() {
//        return new DirectChannel();
//    }
//
//    @Bean
//    public MessageChannel errorChannel() {
//        return new DirectChannel();
//    }
//
//    @Bean
//    public MessageChannel replyChannel() {
//        return new DirectChannel();
//    }

}
