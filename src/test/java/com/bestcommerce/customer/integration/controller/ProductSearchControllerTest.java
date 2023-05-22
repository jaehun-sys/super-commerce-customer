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
public class ProductSearchControllerTest {

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


    @Test
    @DisplayName("상품 상세 조회 테스트")
    public void viewDetailProductTest() throws Exception {
        ProductActDto dto = new ProductActDto(42L, 0L, 1L, "");

        String content = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/item/view").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("상품 이름 또는 정보 검색")
    public void searchProductTest() throws Exception{

        ProductActDto dto = new ProductActDto(42L, 0L, 0L, "나이키");

        String content = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/item/search").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
