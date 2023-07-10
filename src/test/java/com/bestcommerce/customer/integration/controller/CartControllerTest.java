package com.bestcommerce.customer.integration.controller;

import com.bestcommerce.cart.entity.CartKey;
import com.bestcommerce.cart.dto.CartDto;
import com.bestcommerce.cart.dto.CartItemDto;
import com.bestcommerce.cart.dto.CartKeyDto;
import com.bestcommerce.cart.repository.CartRepository;
import com.bestcommerce.cart.service.CartService;
import com.bestcommerce.customer.util.TestUtilService;
import com.bestcommerce.util.converter.DtoConverter;
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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private DtoConverter dtoConverter;

    @Autowired
    private TestUtilService testUtilService;

    @RegisterExtension
    final RestDocumentationExtension restDocumentation = new RestDocumentationExtension("build/generated-snippets");

    @BeforeEach
    void initial(RestDocumentationContextProvider restDocumentation) throws Exception {
        mockMvc = testUtilService.loginWithJwtToken(mockMvc,objectMapper,restDocumentation);
    }

    @DisplayName("장바구니 담는 테스트")
    @Test
    public void insertAccountInfoTest() throws Exception {
        int productCount = 7;
        Long customerId = 42L;
        Long quantityId = 2L;

        CartDto dto = new CartDto(productCount,customerId,quantityId);

        String content = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/cart/put").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(document("cart/putProduct",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        CartKey cartKey = new CartKey(customerId, quantityId);

        CartDto resultCartDto = dtoConverter.toCartDto(cartRepository.getCartByCartKey(cartKey));

        assertThat(resultCartDto.getCustomerId()).isEqualTo(customerId);
        assertThat(resultCartDto.getQuantityId()).isEqualTo(quantityId);
    }

    @DisplayName("장바구니 리스트 조회 테스트")
    @Test
    public void getCartListByCustomerIdTest() throws Exception{
        Long customerId = 40L;

        CartItemDto dto = new CartItemDto(customerId,"");

        String content = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/cart/list").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(document("cart/myCartList",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        List<CartItemDto> cartItemDtoList = cartService.getCartList(customerId);

        assertThat(2).isEqualTo(cartItemDtoList.size());
    }

    @DisplayName("장바구니 리스트 삭제 테스트")
    @Test
    public void deleteCartListTest() throws Exception{

        List<CartKeyDto> dto = new ArrayList<>();

        dto.add(new CartKeyDto(42L,2L));
        dto.add(new CartKeyDto(42L,3L));

        String content = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/cart/delete").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(document("cart/deleteProductFromMyCart",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        List<CartItemDto> cartItemDtoList = cartService.getCartList(40L);
        assertThat(1).isEqualTo(cartItemDtoList.size());
    }
}
