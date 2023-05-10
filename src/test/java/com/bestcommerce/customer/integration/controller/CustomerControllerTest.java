package com.bestcommerce.customer.integration.controller;

import com.bestcommerce.customer.dto.CustomerDto;
import com.bestcommerce.customer.entity.Customer;
import com.bestcommerce.customer.repository.CustomerRepository;
import com.bestcommerce.customer.service.CustomerService;
import com.bestcommerce.member.dto.MemberLoginDto;
import com.bestcommerce.member.service.MemberDetailService;
import com.bestcommerce.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MemberDetailService memberDetailService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    void initial() throws Exception {

        MemberLoginDto memberLoginDto = new MemberLoginDto("test01","1234");

        String content = objectMapper.writeValueAsString(memberLoginDto);

        String result = mockMvc.perform(post("/member/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andReturn().getResponse().getContentAsString();

        String token = new JSONObject(result).getString("accessToken");

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .defaultRequest(get("/").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .defaultRequest(post("/").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .build();
    }


    @DisplayName("회원 가입 테스트")
    @Test
    public void insertAccountInfoTest() throws Exception {

        String testEmail = "test02";
        String testPassword = "1234";
        String testName = "테스트계정";
        String testNumber = "010-0001-0001";
        String testBirthDate = "20010101";
        Character testAuthYn ='N';

        CustomerDto dto = new CustomerDto(1L, testName, testEmail,testPassword,testNumber,testBirthDate,testAuthYn, "","");

        String content = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/account/register").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        UserDetails member = memberDetailService.loadUserByUsername(testEmail);

        assertThat(member.getUsername()).isEqualTo(testEmail);

        customerRepository.deleteCustomerByMember(memberService.findMember(testEmail));
    }


    @DisplayName("회원정보 수정 테스트")
    @Test
    public void updateCustomerTest() throws Exception{

        CustomerDto dto = new CustomerDto(40L, "test계정","","","017-012-0384","2003.07.18",'N',"","");

        String content = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/account/update").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        Customer modifyCustomer = customerService.getOneCustomerInfo(40L);

        assertThat(dto.getCustomerName()).isEqualTo(modifyCustomer.getCuName());
        assertThat(dto.getCustomerTelNumber()).isEqualTo(modifyCustomer.getCuTelNumber());
        assertThat(dto.getCustomerBirthDate()).isEqualTo(modifyCustomer.getBirthdate());
        assertThat(LocalDate.now().toString()).isEqualTo(modifyCustomer.getModifyDate());
    }
}
