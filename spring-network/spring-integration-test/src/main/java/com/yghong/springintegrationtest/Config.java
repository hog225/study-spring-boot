//package com.yghong.springintegrationtest;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.integration.annotation.*;
//import org.springframework.integration.channel.DirectChannel;
//import org.springframework.integration.config.EnableIntegration;
//import org.springframework.integration.ip.tcp.TcpInboundGateway;
//import org.springframework.integration.ip.tcp.TcpOutboundGateway;
//import org.springframework.integration.ip.tcp.connection.AbstractClientConnectionFactory;
//import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
//import org.springframework.integration.ip.tcp.connection.TcpNetClientConnectionFactory;
//import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.MessageHandler;
//
//import javax.annotation.PostConstruct;
// // 테스트가 필요 하다면 주석을 해지 한다.
//@EnableIntegration
//@IntegrationComponentScan
//@Configuration
//public class Config {
//
//    @MessagingGateway(defaultRequestChannel = "toTcp")
//    public interface Gateway {
//
//        String viaTcp(String in);
//
//    }
//
//    /**
//     *  toTcp 채널에 있는 메시지를 수신한다
//     */
//    @Bean
//    @ServiceActivator(inputChannel = "toTcp")
//    public MessageHandler tcpOutGate(AbstractClientConnectionFactory connectionFactory) {
//        TcpOutboundGateway gate = new TcpOutboundGateway();
//        gate.setConnectionFactory(connectionFactory);
//        return gate;
//    }
//
//    @Bean
//    public TcpInboundGateway tcpInGate(AbstractServerConnectionFactory connectionFactory) {
//        TcpInboundGateway inGate = new TcpInboundGateway();
//        inGate.setConnectionFactory(connectionFactory);
//        // request가 들어오는 채널
//        inGate.setRequestChannel(fromTcp());
//        return inGate;
//    }
//
//    @Bean
//    public MessageChannel fromTcp() {
//        return new DirectChannel();
//    }
//
//    @MessageEndpoint
//    public static class Echo {
//
//        // fromTcp 채널에 있는 메시지를 수신한다
//        @Transformer(inputChannel = "fromTcp", outputChannel = "toTcp")
//        public byte[] convert(byte[] bytes) {
//            return bytes;
//        }
//
//    }
//
//    @Bean
//    public AbstractClientConnectionFactory clientCF() {
//        return new TcpNetClientConnectionFactory("172.16.48.131", 12345);
//    }
//
//    @Bean
//    public AbstractServerConnectionFactory serverCF() {
//        return new TcpNetServerConnectionFactory(1234);
//    }
//
//}