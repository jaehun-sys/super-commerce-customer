package com.bestcommerce.customer.integration.service.payment;

import com.bestcommerce.customer.dto.PaymentDto;
import com.bestcommerce.customer.service.payment.PaymentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Test
    @DisplayName("대량 주문 테스트")
    void saveOrderListTest(){
        List<PaymentDto> paymentDtoList = new ArrayList<>();
        Long customerId = 1L;
        Long productId = 1L;
        Long[] sizeIdArray = {1L,2L,3L,4L,5L,6L};

        for(int i = 0; i < 100000; i++){
            paymentDtoList.add(new PaymentDto(0L,0L,customerId,productId,sizeIdArray[(int)(Math.random()*6)], (int)(Math.random()*6), (int)(Math.random()*50000)));
        }

        paymentService.save(paymentDtoList);


    }
}
