package com.bestcommerce.customer.integration.controller;

import com.bestcommerce.payment.dto.PaymentLogDto;
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
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Rollback
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

}
