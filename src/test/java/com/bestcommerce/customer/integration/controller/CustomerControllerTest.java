package com.bestcommerce.customer.integration.controller;

import com.bestcommerce.customer.dto.CustomerDto;
import com.bestcommerce.customer.entity.Customer;
import com.bestcommerce.customer.repository.CustomerRepository;
import com.bestcommerce.customer.service.CustomerService;
import com.bestcommerce.customer.util.TestUtilService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private TestUtilService testUtilService;

    @RegisterExtension
    final RestDocumentationExtension restDocumentation = new RestDocumentationExtension("build/generated-snippets");

    @BeforeEach
    void initial(RestDocumentationContextProvider restDocumentation) throws Exception {
        mockMvc = testUtilService.loginWithJwtToken(mockMvc,objectMapper,restDocumentation);
    }

    @DisplayName("회원정보 수정 테스트")
    @Test
    public void updateCustomerTest() throws Exception{

        CustomerDto dto = new CustomerDto(40L, "test계정","","","017-0123-4567","2003.07.18","","");

        String content = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/account/update").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(document("account/updateMyInformation",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        Customer modifyCustomer = customerService.getOneCustomerInfo(40L);

        assertThat(dto.getCustomerName()).isEqualTo(modifyCustomer.getCuName());
        assertThat(dto.getCustomerTelNumber()).isEqualTo(modifyCustomer.getCuTelNumber());
        assertThat(dto.getCustomerBirthDate()).isEqualTo(modifyCustomer.getBirthdate());
        assertThat(LocalDate.now().toString()).isEqualTo(modifyCustomer.getModifyDate());
    }

    @DisplayName("회원 정보 조회")
    @Test
    public void getCustomerTest() throws Exception{

        String customerEmail = "dudtkd0219@gmail.com";
        CustomerDto dto = new CustomerDto(0L,"",customerEmail,"","","","","");

        String content = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/account/get").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(document("account/getMyInformation",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
}
