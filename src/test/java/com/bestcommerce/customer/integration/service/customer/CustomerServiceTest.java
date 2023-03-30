package com.bestcommerce.customer.integration.service.customer;

import com.bestcommerce.customer.domain.Customer;
import com.bestcommerce.customer.dto.CustomerDto;
import com.bestcommerce.customer.service.customer.CustomerService;
import com.bestcommerce.customer.util.EntityConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EntityConverter entityConverter;

    @Test
    @DisplayName("계정 정보 저장이 가능해야 한다.")
    public void joinTestCase01() throws Exception{
        CustomerDto expected = new CustomerDto(1L, "test@gmail.com","test1234","test","010-2222-2222","19960131",'N',"","");

        customerService.save(entityConverter.toCustomer(expected));

        Customer actual = customerService.getOneCustomerInfo(expected.getCustomerEmail());

        assertEquals(expected.getCustomerEmail(),actual.getCuEmail());
        assertEquals(expected.getCustomerPassword(),actual.getPassword());
        assertEquals(expected.getCustomerName(),actual.getCuName());
        assertEquals(expected.getCustomerTelNumber(),actual.getCuTelNumber());
        assertEquals(expected.getCustomerBirthDate(),actual.getBirthdate());
        assertEquals(expected.getAuthYn(),actual.getAuthYn());
    }
}
