//package com.yghong.springintegrationtest;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.integration.annotation.IntegrationComponentScan;
//import org.springframework.integration.channel.DirectChannel;
//import org.springframework.integration.config.EnableIntegration;
//import org.springframework.integration.dsl.IntegrationFlow;
//import org.springframework.integration.dsl.IntegrationFlows;
//import org.springframework.integration.ip.tcp.TcpInboundGateway;
//import org.springframework.integration.ip.tcp.TcpOutboundGateway;
//import org.springframework.integration.ip.tcp.TcpSendingMessageHandler;
//import org.springframework.integration.ip.tcp.connection.TcpNetClientConnectionFactory;
//import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//@EnableIntegration
//@IntegrationComponentScan
//@Configuration
//public class TcpProxyConfiguration {
//
//    @Bean
//    public TcpNetClientConnectionFactory connectionFactory() {
//        return new TcpNetClientConnectionFactory("172.16.48.131", 12345);
//    }
//
//    @Bean
//    public TcpNetServerConnectionFactory serverConnectionFactory() {
//        return new TcpNetServerConnectionFactory(9999);
//    }
//
//    @Bean
//    public TcpSendingMessageHandler outboundGateway(TcpNetClientConnectionFactory connectionFactory) {
//        return new TcpSendingMessageHandler();
//    }
//
//    @Bean
//    public MessageChannel toTcp() {
//        return new DirectChannel();
//    }
//
//    @Bean
//    public MessageChannel fromTcp() {
//        return new DirectChannel();
//    }
//
//    @Bean
//    public TcpInboundGateway tcpInboundGateway(TcpNetServerConnectionFactory serverConnectionFactory) {
//        TcpInboundGateway gateway = new TcpInboundGateway();
//        gateway.setConnectionFactory(serverConnectionFactory);
//        gateway.setRequestChannel(fromTcp());
//        return gateway;
//    }
//
//    @Bean
//    public TcpOutboundGateway tcpOutboundGateway(TcpNetClientConnectionFactory connectionFactory) {
//        TcpOutboundGateway gateway = new TcpOutboundGateway();
//        gateway.setConnectionFactory(connectionFactory);
//        gateway.setReplyChannel(fromTcp());
//        return gateway;
//    }
//
//    @Bean
//    public IntegrationFlow tcpProxyFlow(TcpSendingMessageHandler outboundGateway, MessageChannel toTcp,
//                                        TcpInboundGateway tcpInboundGateway, TcpOutboundGateway tcpOutboundGateway) {
//        return IntegrationFlows.from(toTcp)
//                .handle(outboundGateway)
//                .handle(tcpOutboundGateway)
//                .handle(tcpInboundGateway)
//                .get();
//    }
//
//}