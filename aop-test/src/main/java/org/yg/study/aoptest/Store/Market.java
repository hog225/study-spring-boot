package org.yg.study.aoptest.Store;

import org.springframework.stereotype.Component;
import org.yg.study.aoptest.User;
import org.yg.study.aoptest.aspect.annotation.AlarmGreetingMachine;
import org.yg.study.aoptest.decoratorpattern.AlarmGreetingMachineDecorator;
import org.yg.study.aoptest.decoratorpattern.DancingGreetingMachineDecorator;
import org.yg.study.aoptest.proxypattern.GreetingMachine;
import org.yg.study.aoptest.proxypattern.GreetingMachineProxy;
import org.yg.study.aoptest.proxypattern.GreetingMachineReal;

@Component
public class Market extends Store{

    private String name;
    private int visitCount=0;

    public String operationTime(){
        return "8-9";
    }

    @AlarmGreetingMachine
    @Override
    public void visitedBy(User user){
//        // 방문이력 - 부가 기능
//        System.out.println(user.getName() + " 방문"); 

        // 인사 - 핵심 기능
        greeting();
        visitCount++;
    }

    // 핵심 기능 
    public void greeting(){
        System.out.println("스토어에 어서 오세요 !!!!!!!!!!!!");
    }

    @Override
    public boolean isVIP(User user){
        return visitCount > 10;
    }

    public void greeting(User user){
    

        // Proxy 패턴이든 Decorator 패턴이든 부가 기능을 하기 위해 코드를 호출해 줘야 한다. 

        // Proxy
        //GreetingMachine greetingMachine = new GreetingMachineProxy(); // 부가 기능 + 핵심 기능 
        // GreetingMachine greetingMachine = new GreetingMachineReal();// 핵심 기능 
        // greetingMachine.greet(user);

        // Decorator Pattern 
        // GreetingMachine greetingMachine = new DancingGreetingMachineDecorator( new AlarmGreetingMachineDecorator(new GreetingMachineReal()));
        // greetingMachine.greet(user);



    }


}
