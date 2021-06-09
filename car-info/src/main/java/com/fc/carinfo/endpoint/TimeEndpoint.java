package com.fc.carinfo.endpoint;

import java.time.LocalDateTime;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

//actuator 관련 내용 
@Endpoint(id = "time")
public class TimeEndpoint {
    
    @ReadOperation
    public LocalDateTime getNow(){
        return LocalDateTime.now();
    }
}
