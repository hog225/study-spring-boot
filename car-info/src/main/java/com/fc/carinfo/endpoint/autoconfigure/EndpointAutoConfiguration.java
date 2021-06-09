package com.fc.carinfo.endpoint.autoconfigure;

import com.fc.carinfo.endpoint.TimeEndpoint;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndpointAutoConfiguration {
    @Bean
    public TimeEndpoint timeEndpoint(){
        return new TimeEndpoint();
    }
}
