package com.bestcommerce.customer.integration.service.payment;

import com.bestcommerce.customer.domain.Payment;
import com.bestcommerce.customer.dto.PaymentDto;
import com.bestcommerce.customer.service.payment.PaymentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@SpringBootTest
public class PaymentServiceTest {

    private static final Logger log = LoggerFactory.getLogger(PaymentServiceTest.class);

    @Autowired
    private PaymentService paymentService;

    @Test
    @DisplayName("대량 주문 테스트")
    void saveBigOrderListTest(){
        List<PaymentDto> paymentDtoList = new ArrayList<>();
        Long customerId = 1L;
        Long productId = 1L;
        Long[] sizeIdArray = {1L,2L,3L,4L,5L,6L};

        for(int i = 0; i < 100000; i++){
            paymentDtoList.add(new PaymentDto(0L,0L,customerId,productId,sizeIdArray[(int)(Math.random()*6)], (int)(Math.random()*6), (int)(Math.random()*50000)));
        }

        paymentService.save(paymentDtoList);
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

        List<PaymentDto> onePaymentDtoList = new ArrayList<>();
        List<PaymentDto> twoPaymentDtoList = new ArrayList<>();

        onePaymentDtoList.add(onePaymentDto);
        twoPaymentDtoList.add(twoPaymentDto);

        log.info("동시성 테스트 실행");
        service.execute(() -> {
            paymentService.save(onePaymentDtoList);
            latch.countDown();
        });
        service.execute(() -> {
            paymentService.save(twoPaymentDtoList);
            latch.countDown();
        });

        latch.await();

        log.info("payment 동시성 테스트 결과 검증");
        List<Payment> paymentList = paymentService.findAllPaymentList();
        Assertions.assertThat(paymentList.size()).isEqualTo(1);
    }
}
