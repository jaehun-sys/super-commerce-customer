package com.bestcommerce.customer.integration.service.payment;

import com.bestcommerce.member.dto.MemberLoginDto;
import org.assertj.core.api.Assertions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentServiceTest {

    private static final Logger log = LoggerFactory.getLogger(PaymentServiceTest.class);

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getThreadOneToken(){
        String loginUrl = "http://localhost:"+port+"/member/login";

        MemberLoginDto memberLoginDto = new MemberLoginDto("test01","1234");

        ResponseEntity<HashMap> response = restTemplate.postForEntity(loginUrl, memberLoginDto, HashMap.class);

        return Objects.requireNonNull(response.getBody()).get("accessToken").toString();
    }

    private String getThreadTwoToken(){
        String loginUrl = "http://localhost:"+port+"/member/login";

        MemberLoginDto memberLoginDto = new MemberLoginDto("test02","1234");

        ResponseEntity<HashMap> response = restTemplate.postForEntity(loginUrl, memberLoginDto, HashMap.class);

        return Objects.requireNonNull(response.getBody()).get("accessToken").toString();
    }

    @Test
    @DisplayName("주문 동시성 테스트")
    void orderConcurrencyTest() throws InterruptedException, JSONException {

        log.info("order product 동시성 테스트 준비");
        final int numberOfThreads = 2;
        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        log.info("size id가 3인 상품을 주문할 것이며 해당 상품은 총 잔여수량이 1개임");
        JSONObject oneJsonObject = new JSONObject();
        oneJsonObject.put("paymentId","0");
        oneJsonObject.put("paymentLogId","0");
        oneJsonObject.put("customerId","40");
        oneJsonObject.put("productId","1");
        oneJsonObject.put("sizeId","3");
        oneJsonObject.put("productCount","1");
        oneJsonObject.put("productPrice","200");

        JSONObject twoJsonObject = new JSONObject();
        twoJsonObject.put("paymentId","0");
        twoJsonObject.put("paymentLogId","0");
        twoJsonObject.put("customerId","42");
        twoJsonObject.put("productId","1");
        twoJsonObject.put("sizeId","3");
        twoJsonObject.put("productCount","1");
        twoJsonObject.put("productPrice","200");

        JSONObject oneJsonList = new JSONObject();
        JSONArray oneJsonArray = new JSONArray();
        oneJsonArray.put(oneJsonObject);
        oneJsonList.put("orderList",oneJsonArray);

        JSONObject twoJsonList = new JSONObject();
        JSONArray twoJsonArray = new JSONArray();
        twoJsonArray.put(twoJsonObject);
        twoJsonList.put("orderList",twoJsonArray);

        String testUrl = "http://localhost:"+port+"/pay/save";


        HttpHeaders threadOneHeaders = new HttpHeaders();
        threadOneHeaders.setContentType(MediaType.APPLICATION_JSON);
        threadOneHeaders.setBearerAuth(getThreadOneToken());
        HttpEntity<String> oneRequest = new HttpEntity<>(oneJsonList.toString(), threadOneHeaders);

        HttpHeaders threadTwoHeaders = new HttpHeaders();
        threadTwoHeaders.setContentType(MediaType.APPLICATION_JSON);
        threadTwoHeaders.setBearerAuth(getThreadTwoToken());
        HttpEntity<String> twoRequest = new HttpEntity<>(twoJsonList.toString(), threadTwoHeaders);

        log.info("동시성 테스트 실행");
        final String[] responseOne = new String[1];
        final String[] responseTwo = new String[1];
        service.execute(() -> {
            ResponseEntity<Object> response = restTemplate.exchange(testUrl, HttpMethod.POST, oneRequest, Object.class);
            responseOne[0] = response.getBody().toString();
            latch.countDown();
        });
        service.execute(() -> {
            ResponseEntity<Object> response = restTemplate.exchange(testUrl, HttpMethod.POST, twoRequest, Object.class);
            responseTwo[0] = response.getBody().toString();
            latch.countDown();
        });

        latch.await();

        log.info("payment 동시성 테스트 결과 검증");
        System.out.println("Thread 1 Status : "+responseOne[0]);
        System.out.println("Thread 2 Status : "+responseTwo[0]);
        Assertions.assertThat(responseOne[0]).isNotEqualTo(responseTwo[0]);
    }
}
