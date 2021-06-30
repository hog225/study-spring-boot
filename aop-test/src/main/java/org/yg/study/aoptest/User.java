package org.yg.study.aoptest;

import org.yg.study.aoptest.Store.Library;
import org.yg.study.aoptest.Store.Market;

public class User {
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String greeting(){
        return "hello";
    }

    public void visitTo(Market store){
        
        store.visitedBy(this);
    }

    public void visitTo(Library lib){
        
        lib.visitedBy(this);
    }
}
