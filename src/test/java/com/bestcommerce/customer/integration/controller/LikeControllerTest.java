package com.bestcommerce.customer.integration.controller;

import com.bestcommerce.customer.util.TestUtilService;
import com.bestcommerce.product.dto.ProductActDto;
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

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class LikeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestUtilService testUtilService;

    @RegisterExtension
    final RestDocumentationExtension restDocumentation = new RestDocumentationExtension("build/generated-snippets");

    @BeforeEach
    void initial(RestDocumentationContextProvider restDocumentation) throws Exception {
        mockMvc = testUtilService.loginWithJwtToken(mockMvc,objectMapper,restDocumentation);
    }

    @DisplayName("상품 좋아요 및 취소 테스트")
    @Test
    void likeProductTest() throws Exception{

        ProductActDto dto = new ProductActDto(42L, 0L,7L,"");

        String content = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/like/product").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(document("like/productLikeOrCancel",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @DisplayName("내가 좋아요한 상품 리스트")
    @Test
    void likeListTest() throws Exception{

        ProductActDto dto = new ProductActDto(42L, 0L,0L,"");

        String content = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/like/product/list").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("브랜드 좋아요 및 취소 테스트")
    @Test
    void likeBrandTest() throws Exception{

        ProductActDto dto = new ProductActDto(42L, 1L,0L,"");

        String content = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/like/brand").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
