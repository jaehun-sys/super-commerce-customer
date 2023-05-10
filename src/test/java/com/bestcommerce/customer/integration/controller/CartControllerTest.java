package com.bestcommerce.customer.integration.controller;

import com.bestcommerce.cart.entity.CartKey;
import com.bestcommerce.cart.dto.CartDto;
import com.bestcommerce.cart.dto.CartItemDto;
import com.bestcommerce.cart.dto.CartKeyDto;
import com.bestcommerce.cart.repository.CartRepository;
import com.bestcommerce.cart.service.CartService;
import com.bestcommerce.member.dto.MemberLoginDto;
import com.bestcommerce.util.converter.DtoConverter;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private DtoConverter dtoConverter;

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

    @DisplayName("장바구니 담는 테스트")
    @Test
    public void insertAccountInfoTest() throws Exception {
        int productCount = 8;
        Long sizeId = 3L;
        Long customerId = 40L;
        Long productId = 1L;

        CartDto dto = new CartDto(productCount,sizeId,customerId,productId);

        String content = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/cart/put").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        CartKey cartKey = new CartKey(customerId, productId, sizeId);

        CartDto resultCartDto = dtoConverter.toCartDto(cartRepository.getCartByCartKey(cartKey));

        assertThat(resultCartDto.getCustomerId()).isEqualTo(customerId);
        assertThat(resultCartDto.getProductId()).isEqualTo(productId);
        assertThat(resultCartDto.getSizeId()).isEqualTo(sizeId);
    }

    @DisplayName("장바구니 리스트 조회 테스트")
    @Test
    public void getCartListByCustomerIdTest() throws Exception{
        Long customerId = 40L;

        CartItemDto dto = new CartItemDto(customerId,"",0L,"",0,0L,"",0,0L,0L,"",0L,"",0);

        String content = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/cart/list").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        List<CartItemDto> cartItemDtoList = cartService.getCartList(customerId);

        assertThat(3).isEqualTo(cartItemDtoList.size());
    }

    @DisplayName("장바구니 리스트 삭제 테스트")
    @Test
    public void deleteCartListTest() throws Exception{

        List<CartKeyDto> dto = new ArrayList<>();

        dto.add(new CartKeyDto(40L,1L,2L));
        dto.add(new CartKeyDto(40L,1L,3L));

        String content = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/cart/delete").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        List<CartItemDto> cartItemDtoList = cartService.getCartList(40L);
        assertThat(1).isEqualTo(cartItemDtoList.size());
    }
}
