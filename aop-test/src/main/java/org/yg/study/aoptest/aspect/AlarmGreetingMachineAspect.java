package org.yg.study.aoptest.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.yg.study.aoptest.User;
import org.yg.study.aoptest.Store.Market;
import org.yg.study.aoptest.Store.Store;
import org.yg.study.aoptest.aspect.annotation.AlarmGreetingMachine;

@Aspect
@Component // Bean  으로 등록 
public class AlarmGreetingMachineAspect {

    // Join Point 지정 애노테이션 
    
    // @After()
    // @AfterReturning
    // @AfterThrowing
    // @Around// 정확한 지점을 설정 하지 않음 
    // value 는 Target Object
    @Before(value = "@annotation(org.yg.study.aoptest.aspect.annotation.AlarmGreetingMachine)")
    public void alarm(JoinPoint joinPoint){
        // Runtime에 수행 
        Object[] args = joinPoint.getArgs();
        User user = (User)args[0];
        
        Store store = (Store)joinPoint.getTarget();
        if (store.isVIP(user)){
            System.out.println("VIPPPPPPP!!!!!!!!!!!! 가 되었음 ");
        }        

        System.out.println(user.getName() + " 방문" );
    }
}
