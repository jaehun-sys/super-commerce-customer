package com.bestcommerce.customer.repository;

import com.bestcommerce.customer.config.QueryDslTestConfig;
import com.bestcommerce.customer.account.Customer;
import com.bestcommerce.customer.account.CustomerDto;
import com.bestcommerce.customer.account.CustomerRepository;
import com.bestcommerce.customer.account.CustomerRepositorySupport;
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

        CustomerDto customerDto = new CustomerDto(9L, "kazino@gmail.com","8888","상구야","","",'N',"","");

        customerRepositorySupport.updateCustomer(customerDto);

        Customer updateCheckCustomer = customerRepository.findById(customerDto.getCustomerId()).orElseGet(Customer::new);

        assertThat(customerDto.getCustomerName()).isEqualTo(updateCheckCustomer.getCuName());
        assertThat(customerDto.getCustomerPassword()).isEqualTo(updateCheckCustomer.getPassword());
    }
}
