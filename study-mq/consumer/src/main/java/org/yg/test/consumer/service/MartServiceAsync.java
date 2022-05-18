package org.yg.test.consumer.service;


import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

// Queue로 부터 받은 메시지를 처리하기 전에 Ack를 보내게 된다.
@Service
@EnableAsync
@Log4j2
public class MartServiceAsync {

    @Async
    public void accept(Message c){
        System.out.println("accept: " + c);
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            log.info(e);
        }

    }
}
