package com.bestcommerce.customer.controller;

import com.bestcommerce.customer.dto.CartDto;
import com.bestcommerce.customer.service.account.AccountService;
import com.bestcommerce.customer.service.product.ProductSelectService;
import com.bestcommerce.customer.service.size.SizeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Rollback
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CartControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductSelectService productSelectService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private SizeService sizeService;

    @DisplayName("장바구니 담는 테스트")
    @Test
    public void insertAccountInfoTest() throws Exception {
        int productCount = 4;
        Long sizeId = 1L;
        Long customerId = 1L;
        Long productId = 1L;

        CartDto cartDto = new CartDto(productCount,sizeId,customerId,productId);

        String testUrl = "http://localhost:"+port+"/cart/put";

        ResponseEntity<Object> response = restTemplate.postForEntity(testUrl, cartDto, Object.class);

        /*
        * 
        * !!!
        * TODO 설계 허점
        * */

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


}
