package com.bestcommerce.customer.config;

import com.bestcommerce.customer.repository.CustomerRepositorySupport;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@TestConfiguration
public class QueryDslTestConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory(){
        return new JPAQueryFactory(entityManager);
    }

    @Bean
    public CustomerRepositorySupport customerRepositorySupport(JPAQueryFactory jpaQueryFactory){
        return new CustomerRepositorySupport(jpaQueryFactory);
    }
}
