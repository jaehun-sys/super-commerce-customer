package com.bestcommerce.customer.integration.controller;

import com.bestcommerce.customer.dto.CustomerDto;
import com.bestcommerce.customer.repository.CustomerRepository;
import com.bestcommerce.customer.util.TestUtilService;
import com.bestcommerce.member.dto.MemberLoginDto;
import com.bestcommerce.member.service.MemberDetailService;
import com.bestcommerce.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class CustomerUserDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MemberDetailService memberDetailService;

    @Autowired
    private MemberService memberService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private TestUtilService testUtilService;

    @Value("${testUtil.login.id}")
    private String testId;

    @Value("${testUtil.login.pw}")
    private String testPwd;

    @RegisterExtension
    final RestDocumentationExtension restDocumentation = new RestDocumentationExtension("build/generated-snippets");

    @BeforeEach
    void initial(RestDocumentationContextProvider restDocumentation){
        mockMvc = testUtilService.setRestDocumentation(restDocumentation);
    }

    @DisplayName("회원 가입 테스트")
    @Test
    public void insertAccountInfoTest() throws Exception {

        String testEmail = "test05";
        String testPassword = "1234";
        String testName = "테스트계정5";
        String testNumber = "010-0105-0005";
        String testBirthDate = "20021213";

        CustomerDto dto = new CustomerDto(0L, testName, testEmail,testPassword,testNumber,testBirthDate, "","");

        String content = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/account/register").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(document("account/customerRegister",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        UserDetails member = memberDetailService.loadUserByUsername(testEmail);

        assertThat(member.getUsername()).isEqualTo(testEmail);

        customerRepository.deleteCustomerByMember(memberService.findMember(testEmail));
    }

    @DisplayName("로그인 테스트")
    @Test
    void LoginTest() throws Exception {

        String content = objectMapper.writeValueAsString(new MemberLoginDto(testId,testPwd));

        mockMvc.perform(post("/member/login").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(document("member/customerLogin",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

}
