package com.bestcommerce.customer.integration.service.payment;

import com.bestcommerce.util.DtoList;
import com.bestcommerce.payment.dto.PaymentDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
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

    @Test
    @DisplayName("주문 동시성 테스트")
    void orderConcurrencyTest() throws InterruptedException {
        log.info("order product 동시성 테스트 시작");

        log.info("order product 동시성 테스트 준비");
        final int numberOfThreads = 2;
        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        log.info("size id가 3인 상품을 주문할 것이며 해당 상품은 총 잔여수량이 1개임");
        PaymentDto onePaymentDto  = new PaymentDto(0L,0L,1L,1L,3L, 1, 200);
        PaymentDto twoPaymentDto = new PaymentDto(0L,0L,1L,1L,3L, 1, 200);

        String testUrl = "http://localhost:"+port+"/pay/save";

        List<PaymentDto> oneOrderList = new ArrayList<>();
        List<PaymentDto> twoOrderList = new ArrayList<>();
        oneOrderList.add(onePaymentDto);
        twoOrderList.add(twoPaymentDto);

        DtoList oneDtoList = new DtoList(oneOrderList);
        DtoList twoDtoList = new DtoList(twoOrderList);

        log.info("동시성 테스트 실행");
        final String[] responseOne = new String[1];
        final String[] responseTwo = new String[1];
        service.execute(() -> {
            ResponseEntity<String> response = restTemplate.postForEntity(testUrl, oneDtoList, String.class);
            responseOne[0] = response.getBody();
            latch.countDown();
        });
        service.execute(() -> {
            ResponseEntity<String> response = restTemplate.postForEntity(testUrl, twoDtoList, String.class);
            responseTwo[0] = response.getBody();
            latch.countDown();
        });

        latch.await();

        log.info("payment 동시성 테스트 결과 검증");
        System.out.println("Thread 1 Status : "+responseOne[0]);
        System.out.println("Thread 2 Status : "+responseTwo[0]);
        Assertions.assertThat(responseOne[0]).isNotEqualTo(responseTwo[0]);
    }
}
