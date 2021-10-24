package org.yg.study.JPAsample.Bean;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class QueryDslBean {

    @PersistenceContext
    private EntityManager em;


    @Bean
    public JPAQueryFactory QueryDslBeanMake(){
        return new JPAQueryFactory(em);
    }
}
