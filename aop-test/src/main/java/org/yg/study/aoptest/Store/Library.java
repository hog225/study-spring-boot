package org.yg.study.aoptest.Store;

import org.springframework.stereotype.Component;
import org.yg.study.aoptest.User;
import org.yg.study.aoptest.aspect.annotation.AlarmGreetingMachine;

@Component
public class Library extends Store{
    private String name;
    private int visitCount;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @AlarmGreetingMachine
    @Override
    public void visitedBy(User user){
        System.out.println("환영  여기는 "+ getName() + " 도서관임");
        visitCount++;
    }

    @Override
    public boolean isVIP(User user){
        return visitCount > 10;
    }
    
}
