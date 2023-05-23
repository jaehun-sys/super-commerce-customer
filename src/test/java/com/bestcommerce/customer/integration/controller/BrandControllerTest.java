package com.bestcommerce.customer.integration.controller;

import com.bestcommerce.customer.util.TestUtilService;
import com.bestcommerce.product.dto.ProductActDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class BrandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestUtilService testUtilService;

    @BeforeEach
    void initial() throws Exception {
        mockMvc = testUtilService.loginWithJwtToken(mockMvc,objectMapper);
    }

    @DisplayName("브랜드 디테일 페이지 조회 테스트")
    @Test
    void brandDetailTest() throws Exception{
        ProductActDto dto = new ProductActDto(42L, 1L,7L,"");

        String content = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/brand/detail").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
