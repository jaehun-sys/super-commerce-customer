package com.bestcommerce.customer.integration.controller;

import com.bestcommerce.customer.entity.Customer;
import com.bestcommerce.customer.dto.CustomerDto;
import com.bestcommerce.customer.service.CustomerService;
import com.bestcommerce.member.dto.MemberLoginDto;
import com.bestcommerce.member.service.MemberDetailService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Rollback
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MemberDetailService memberDetailService;


    @DisplayName("회원 가입 테스트")
    @Test
    public void insertAccountInfoTest(){
        String testUrl = "http://localhost:"+port+"/account/register";

        String testEmail = "test01";
        String testPassword = "1234";
        String testName = "테스트계정";
        String testNumber = "010-0001-0001";
        String testBirthDate = "20010101";
        Character testAuthYn ='N';

        CustomerDto customerDto = new CustomerDto(1L, testName, testEmail,testPassword,testNumber,testBirthDate,testAuthYn, "","");

        ResponseEntity<String> response = restTemplate.postForEntity(testUrl, customerDto,String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        UserDetails member = memberDetailService.loadUserByUsername(testEmail);

        assertThat(member.getUsername()).isEqualTo(testEmail);
    }


    @DisplayName("회원정보 수정 테스트")
    @Test
    public void updateCustomerTest() throws Exception{

    }
}
