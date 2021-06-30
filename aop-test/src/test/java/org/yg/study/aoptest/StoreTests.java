package org.yg.study.aoptest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.yg.study.aoptest.Store.Market;

@SpringBootTest
public class StoreTests {
    @Autowired
    Market store;

    @Test
    public void storeTest(){
        User user = new User();
        user.setName("My Teacher");

    
        store.visitedBy(user);


    }
}
