package org.yg.study.JPAsample.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@TestConfiguration
public class TestConfig {
    @PersistenceContext
    private EntityManager em;


    @Bean
    public JPAQueryFactory QueryDslBeanMake(){
        return new JPAQueryFactory(em);
    }
}
