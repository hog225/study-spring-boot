package com.fc.carinfo.domain;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



class EntityTest {
    
    static Company mockCompany;

    @BeforeAll
    static void beforeAll(){
        mockCompany = mock(Company.class);
        mockCompany.setCompanyName("GM");
        mockCompany.setCompanyNation("America");
        System.out.println("feeff");
    }

    @Test
    void mockitoTest(){

        
        Company company = mock(Company.class);

        when(company.getCompanyName()).thenReturn("페라리1"); // ginven 조건 getCompanyName 을 했을때 페라리1을 리턴해라
        when(company.getCompanyNation()).thenReturn("이탈리아");

        assertTrue("페라리1".equals(company.getCompanyName()));

        
    }

    @Test
    void mockitoTest2(){


        
    }


}
