package org.yg.study.aoptest.proxypattern;

import org.yg.study.aoptest.User;

public class GreetingMachineProxy implements GreetingMachine{

    private GreetingMachineReal greetingMachineReal;

    public GreetingMachineProxy(){
        this.greetingMachineReal = new GreetingMachineReal();
    }

    @Override
    public void greet(User user) {
        // 부가
        System.out.println(user.getName() + " 방문");
        // 핵심
        greetingMachineReal.greet(user);
    }
}
