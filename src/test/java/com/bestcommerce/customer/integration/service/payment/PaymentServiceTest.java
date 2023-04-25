package com.bestcommerce.customer.integration.service.payment;

import com.bestcommerce.payment.entity.Payment;
import com.bestcommerce.util.DtoList;
import com.bestcommerce.payment.dto.PaymentDto;
import com.bestcommerce.payment.service.PaymentService;
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

    @Autowired
    private PaymentService paymentService;


    @Test
    @DisplayName("대량 주문 테스트")
    void saveBigOrderListTest(){

    }

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
        service.execute(() -> {
            ResponseEntity<String> response = restTemplate.postForEntity(testUrl, oneDtoList, String.class);
            System.out.println("Thread 1 Status : "+response.getBody());
            latch.countDown();
        });
        service.execute(() -> {
            ResponseEntity<String> response = restTemplate.postForEntity(testUrl, twoDtoList, String.class);
            System.out.println("Thread 2 Status : "+response.getBody());
            latch.countDown();
        });

        latch.await();

        log.info("payment 동시성 테스트 결과 검증");
        List<Payment> paymentList = paymentService.findAllPaymentList();
        Assertions.assertThat(paymentList.size()).isEqualTo(1);
    }
}
