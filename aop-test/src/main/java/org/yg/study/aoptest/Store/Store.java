package org.yg.study.aoptest.Store;

import org.yg.study.aoptest.User;

public abstract class Store {

    public abstract void visitedBy(User user);

    public abstract boolean isVIP(User user);

    
}
