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

        String testEmail = "beauty01@naver.com";
        String testPassword = "5678";
        String testName = "iamTest";
        String testNumber = "010-2222-3333";
        String testBirthDate = "19901206";
        Character testAuthYn ='N';

        CustomerDto customerDto = new CustomerDto(testEmail,testPassword,testName,testNumber,testBirthDate,testAuthYn, "","");
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

    @DisplayName("유효한 이메일인지 체크하는 테스트")
    @Test
    public void checkValidEmailTest() throws Exception{
        String testEmail01 = "dudtkd0219@gmail.com";
        String testEmail02 = "zzangman@gmail.com";

        CustomerDto customerDto = new CustomerDto(testEmail01,"","","","",'N',"","");
        String testUrl = "http://localhost:"+port+"/account/check/email";

        ResponseEntity<Object> response = restTemplate.postForEntity(testUrl, customerDto, Object.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(false);

        customerDto = new CustomerDto(testEmail02,"","","","",'N', "","");

        response = restTemplate.postForEntity(testUrl, customerDto, Object.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(true);

    }
}
