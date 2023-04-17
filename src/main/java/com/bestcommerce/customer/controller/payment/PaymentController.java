package com.bestcommerce.customer.controller.payment;

import com.bestcommerce.customer.dto.DtoList;
import com.bestcommerce.customer.dto.PaymentDto;
import com.bestcommerce.customer.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pay")
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    private final PaymentService paymentService;

    @PostMapping("/save")
    public void paymentSave(@RequestBody DtoList dtoList){
        try {
            List<PaymentDto> paymentDtoList = dtoList.getOrderList();
            paymentService.save(paymentDtoList);
        }
        catch (NullPointerException npe){
            log.error("maybe order list is null");
            log.error(npe.getMessage());
        }
    }

}
