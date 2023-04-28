package com.bestcommerce.customer.integration.controller;

import com.bestcommerce.product.dto.ProductDetailDto;
import com.bestcommerce.product.dto.ProductDto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Rollback
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductSearchControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("상품 상세 조회 테스트")
    public void viewDetailProductTest() throws JSONException {
        ProductDto productDto = new ProductDto(1L, "",0,"","",0);

        String testUrl = "http://localhost:"+port+"/item/view";

        ResponseEntity<Object> response = restTemplate.postForEntity(testUrl, productDto, Object.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        System.out.println(response.getBody());

        JSONObject responseObject = new JSONObject((Map) response.getBody());
        JSONObject productJsonObject = responseObject.getJSONObject("productDto");
        JSONArray sizeJsonArray = responseObject.getJSONArray("sizeDtoList");
        System.out.println(productJsonObject);
        for(int i = 0; i < sizeJsonArray.length(); i++){
            System.out.println(sizeJsonArray.getJSONObject(i));
        }
        assertThat(sizeJsonArray.length()).isEqualTo(6);
        assertThat(productJsonObject.getString("productName")).isEqualTo("나이키 티엠포 레전드 9 엘리트 FG");
    }

    @Test
    @DisplayName("상품 이름 또는 정보 검색")
    public void searchProductTest(){
        String searchValue = "나이키";

        String testUrl = "http://localhost:"+port+"/item/search";

        ResponseEntity<Object> response = restTemplate.postForEntity(testUrl, searchValue, Object.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        ArrayList<ProductDetailDto> list = (ArrayList<ProductDetailDto>) response.getBody();
        System.out.println(list);
        assert list != null;
        assertThat(list.size()).isEqualTo(10);
    }
}
