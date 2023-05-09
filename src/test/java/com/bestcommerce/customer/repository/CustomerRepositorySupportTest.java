package com.bestcommerce.customer.repository;

import com.bestcommerce.customer.config.QueryDslTestConfig;
import com.bestcommerce.customer.entity.Customer;
import com.bestcommerce.customer.dto.CustomerDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@Import({QueryDslTestConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositorySupportTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerRepositorySupport customerRepositorySupport;

    @Test
    @DisplayName("회원 정보 UPDATE TEST")
    @Rollback(false)
    void updateCustomerTest(){

    }
}
