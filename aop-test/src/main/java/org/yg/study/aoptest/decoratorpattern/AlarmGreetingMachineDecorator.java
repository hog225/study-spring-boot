package org.yg.study.aoptest.decoratorpattern;

import org.yg.study.aoptest.User;
import org.yg.study.aoptest.proxypattern.GreetingMachine;

public class AlarmGreetingMachineDecorator extends GreetingMachineDecorator {
    public AlarmGreetingMachineDecorator(GreetingMachine greetingMachine){
        super(greetingMachine);
    }
    @Override
    public void greet(User user){
        alarm(user);
        // 핵심 기능에 대해서는 관여하지 않는다. 

        greetingMachine.greet(user);

    }

    void alarm(User user){
        System.out.println(user.getName() + " 방문함 ");
    }
    
}
