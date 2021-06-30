package org.yg.study.aoptest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.yg.study.aoptest.Store.Library;
import org.yg.study.aoptest.Store.Market;

@SpringBootTest
public class LibraryTests {
    @Autowired
    Library library;

    @Autowired
    Market store;

    @Test
    public void storeTest(){
        User user = new User();
        user.setName("홍홍홍");
        library.setName("삼천도서관");

    
        library.visitedBy(user);


    }

    @Test
    public void testVisitToStore(){
        User user = new User();
        user.setName("박박박");

        for (int i=0; i<= 11; i++){
            user.visitTo(library);
            
        }
        
        
    }
}
