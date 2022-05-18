package org.yg.test.consumer.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class MartService {

    public void accept(Message c){
        System.out.println("accept: " + c);
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            log.info(e);
        }

    }
}
