package com.bestcommerce.customer.repository;

import com.bestcommerce.customer.entity.Customer;
import com.bestcommerce.customer.dto.CustomerDto;
import com.querydsl.core.dml.UpdateClause;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;

import java.time.LocalDate;

import static com.bestcommerce.customer.entity.QCustomer.customer;

@Repository
public class CustomerRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public CustomerRepositorySupport(JPAQueryFactory queryFactory) {
        super(Customer.class);
        this.queryFactory = queryFactory;
    }

    @Modifying
    @Transactional
    public void updateCustomer(CustomerDto customerDto){
        UpdateClause<JPAUpdateClause> builder = update(customer);
        setCustomerModifyBuilder(customerDto,builder);
        builder.set(customer.modifyDate, LocalDate.now().toString());
        long count = builder.where(customer.cuId.eq(customerDto.getCustomerId())).execute();
    }

    private void setCustomerModifyBuilder(CustomerDto customerDto, UpdateClause<JPAUpdateClause> builder){
        if(StringUtils.hasText(customerDto.getCustomerName())){
            builder.set(customer.cuName, customerDto.getCustomerName());
        }
        if(StringUtils.hasText(customerDto.getCustomerTelNumber())){
            builder.set(customer.cuTelNumber, customerDto.getCustomerTelNumber());
        }
        if(StringUtils.hasText(customerDto.getCustomerBirthDate())){
            builder.set(customer.birthdate, customerDto.getCustomerBirthDate());
        }
    }
}
