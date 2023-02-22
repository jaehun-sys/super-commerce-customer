package com.bestcommerce.customer.controller;

import com.bestcommerce.customer.domain.Customer;
import com.bestcommerce.customer.dto.CustomerDto;
import com.bestcommerce.customer.service.account.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Rollback
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AccountService accountService;


    @DisplayName("회원 가입 테스트")
    @Test
    public void insertAccountInfoTest() throws Exception{

        String testEmail = "customer01@naver.com";
        String testPassword = "1234";
        String testName = "테스트";
        String testNumber = "010-1111-1111";
        String testBirthDate = "20010913";
        Character testAuthYn ='N';

        CustomerDto customerDto = new CustomerDto(testEmail,testPassword,testName,testNumber,testBirthDate,testAuthYn);
        String testUrl = "http://localhost:"+port+"/account/register";

        ResponseEntity<Long> response = restTemplate.postForEntity(testUrl, customerDto, Long.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Customer customer = accountService.getOneCustomerInfo(testEmail);

        assertThat(customer.getCuEmail()).isEqualTo(testEmail);
        assertThat(customer.getPassword()).isEqualTo(testPassword);
        assertThat(customer.getCuName()).isEqualTo(testName);
        assertThat(customer.getCuTelNumber()).isEqualTo(testNumber);
        assertThat(customer.getBirthdate()).isEqualTo(testBirthDate);
        assertThat(customer.getAuthYn()).isEqualTo(testAuthYn);

        accountService.deleteOneCustomer(testEmail);
    }
}
