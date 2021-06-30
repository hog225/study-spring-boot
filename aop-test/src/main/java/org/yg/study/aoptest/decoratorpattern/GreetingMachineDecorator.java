package org.yg.study.aoptest.decoratorpattern;

import org.yg.study.aoptest.proxypattern.GreetingMachine;

public abstract class GreetingMachineDecorator implements GreetingMachine{
    public GreetingMachine greetingMachine;

    public GreetingMachineDecorator(GreetingMachine greetingMachine){
        this.greetingMachine = greetingMachine;
    }
    
}
