package org.yg.study.aoptest.proxypattern;

import org.yg.study.aoptest.User;
import org.yg.study.aoptest.proxypattern.GreetingMachine;

public class GreetingMachineReal implements GreetingMachine{
    
    @Override
    public void greet(User user){
        System.out.println("어서 오세요 ");
    }
    
}
