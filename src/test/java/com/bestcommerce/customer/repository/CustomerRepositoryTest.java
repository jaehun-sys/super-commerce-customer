package com.bestcommerce.customer.repository;

import com.bestcommerce.customer.domain.Customer;
import com.bestcommerce.customer.dto.CustomerDto;
import com.bestcommerce.customer.repository.domain.CustomerRepository;
import com.bestcommerce.customer.util.EntityConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    private EntityConverter entityConverter;

    @BeforeEach
    public void setup(){
        entityConverter = new EntityConverter();
    }

    @Test
    @DisplayName("회원 가입 repository Test")
    void findByCuEmailTest(){
        String testEmail = "beauty01@naver.com";
        String testPassword = "5678";
        String testName = "iamTest";
        String testNumber = "010-2222-3333";
        String testBirthDate = "19901206";
        Character testAuthYn ='N';

        CustomerDto customerDto = new CustomerDto(1L, testEmail,testPassword,testName,testNumber,testBirthDate,testAuthYn, "","");

        Customer testCustomer = entityConverter.toCustomer(customerDto);

        customerRepository.save(testCustomer);

        Customer testTargetCustomer = customerRepository.findByCuEmail(testEmail);

        assertThat(testCustomer.getCuEmail()).isEqualTo(testTargetCustomer.getCuEmail());
        assertThat(testCustomer.getCuName()).isEqualTo(testTargetCustomer.getCuName());

        customerRepository.deleteCustomerByCuEmail(testEmail);

    }
}
