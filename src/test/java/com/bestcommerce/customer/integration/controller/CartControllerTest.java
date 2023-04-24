package com.bestcommerce.customer.integration.controller;

import com.bestcommerce.cart.CartKey;
import com.bestcommerce.cart.CartDto;
import com.bestcommerce.cart.CartItemDto;
import com.bestcommerce.cart.CartKeyDto;
import com.bestcommerce.cart.CartRepository;
import com.bestcommerce.cart.CartService;
import com.bestcommerce.util.converter.DtoConverter;
import org.json.JSONArray;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Rollback
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CartControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private DtoConverter dtoConverter;

    @DisplayName("장바구니 담는 테스트")
    @Test
    public void insertAccountInfoTest() throws Exception {
        int productCount = 8;
        Long sizeId = 1L;
        Long customerId = 5L;
        Long productId = 7L;

        CartDto cartDto = new CartDto(productCount,sizeId,customerId,productId);
        CartKey cartKey = new CartKey(customerId, productId, sizeId);

        String testUrl = "http://localhost:"+port+"/cart/put";

        ResponseEntity<Object> response = restTemplate.postForEntity(testUrl, cartDto, Object.class);
        CartDto resultCartDto = dtoConverter.toCartDto(cartRepository.getCartByCartKey(cartKey));

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resultCartDto.getCustomerId()).isEqualTo(customerId);
        assertThat(resultCartDto.getProductId()).isEqualTo(productId);
        assertThat(resultCartDto.getSizeId()).isEqualTo(sizeId);
    }

    @DisplayName("장바구니 리스트 조회 테스트")
    @Test
    public void getCartListByCustomerIdTest() throws Exception{
        Long customerId = 1L;

        CartItemDto cartItemDto = new CartItemDto(customerId,"","",0L,"",0,0L,"",0,0L,0L,"",0L,"",0);

        String testUrl = "http://localhost:"+port+"/cart/list";

        ResponseEntity<String> response = restTemplate.postForEntity(testUrl, cartItemDto, String.class);
        List<CartItemDto> cartItemDtoList = cartService.getCartList(customerId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        JSONArray jsonArray = new JSONArray(response.getBody());
        if(cartItemDtoList.isEmpty()){
            System.out.println("장바구니가 비었습니다");
            return;
        }
        assertThat(jsonArray.length()).isEqualTo(cartItemDtoList.size());
        for(int i = 0; i < jsonArray.length(); i++){
            assertThat(cartItemDtoList.get(i).getCustomerName()).isEqualTo(jsonArray.getJSONObject(i).getString("customerName"));
            assertThat(cartItemDtoList.get(i).getProductName()).isEqualTo(jsonArray.getJSONObject(i).getString("productName"));
            assertThat(cartItemDtoList.get(i).getProductCost()).isEqualTo(jsonArray.getJSONObject(i).getInt("productCost"));
        }
    }

    @DisplayName("장바구니 리스트 삭제 테스트")
    @Test
    public void deleteCartListTest() throws Exception{

        List<CartKeyDto> cartKeyDtoList = new ArrayList<>();

        cartKeyDtoList.add(new CartKeyDto(1L,1L,1L));
        cartKeyDtoList.add(new CartKeyDto(1L,3L,1L));

        String testUrl = "http://localhost:"+port+"/cart/delete";

        ResponseEntity<String> response = restTemplate.postForEntity(testUrl, cartKeyDtoList, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<CartItemDto> cartItemDtoList = cartService.getCartList(1L);
        assertThat(cartItemDtoList.isEmpty()).isTrue();
    }
}
