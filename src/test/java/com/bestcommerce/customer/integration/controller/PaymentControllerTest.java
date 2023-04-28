package com.bestcommerce.customer.integration.controller;

import com.bestcommerce.payment.dto.PaymentDto;
import com.bestcommerce.payment.dto.PaymentLogDto;
import com.bestcommerce.util.DtoList;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentControllerTest {

    private static final Logger log = LoggerFactory.getLogger(PaymentControllerTest.class);

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    @DisplayName("가장 최근 주문 내역 조회")
    void getRecentPayListTest() throws JSONException {
        log.info("테스트 시작");

        String testUrl = "http://localhost:"+port+"/pay/get/recent";

        PaymentLogDto paymentLogDto = new PaymentLogDto(27L,1L,0L, "");

        ResponseEntity<String> response = restTemplate.postForEntity(testUrl, paymentLogDto, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        JSONArray jsonArray = new JSONArray(response.getBody());

        for(int i = 0; i < jsonArray.length(); i++){
            assertThat(jsonArray.getJSONObject(i).getString("productName")).isEqualTo("나이키 티엠포 레전드 9 엘리트 FG");
        }

    }

    @Test
    @DisplayName("대량 주문 테스트")
    void saveBigOrderListTest(){

        Long[] customerIdArray = {1L,5L,8L,9L,27L};
        List<PaymentDto> orderList = new ArrayList<>();

        for(long i = 1L; i <= 6L; i++){
            orderList.add(new PaymentDto(0L,0L,
                    customerIdArray[1],
                    1L,
                    i,
                    (int)(Math.random()*10 + 1),
                    0));
        }

        DtoList oneDtoList = new DtoList(orderList);

        String testUrl = "http://localhost:"+port+"/pay/save";
        ResponseEntity<String> response = restTemplate.postForEntity(testUrl, oneDtoList, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(response.getBody());


    }

}
