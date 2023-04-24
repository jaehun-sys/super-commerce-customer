package com.bestcommerce.customer.controller.payment;

import com.bestcommerce.customer.domain.Customer;
import com.bestcommerce.customer.domain.Payment;
import com.bestcommerce.customer.domain.Size;
import com.bestcommerce.customer.dto.DtoList;
import com.bestcommerce.customer.dto.PaymentDto;
import com.bestcommerce.customer.exception.QuantityInsufficientException;
import com.bestcommerce.customer.exception.QuantitySoldOutException;
import com.bestcommerce.customer.service.customer.CustomerService;
import com.bestcommerce.customer.service.payment.PaymentService;
import com.bestcommerce.customer.service.size.SizeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pay")
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    private final PaymentService paymentService;

    private final CustomerService customerService;

    private final SizeService sizeService;

    @PostMapping("/save")
    public String paymentSave(@RequestBody DtoList dtoList){
        try {
            List<PaymentDto> paymentDtoList = dtoList.getOrderList();
            Customer customer = customerService.getOneCustomerInfo(paymentDtoList.get(0).getCustomerId());
            Long[] totalPrice = {0L};
            List<Payment> paymentList = new ArrayList<>();
            paymentConverterForInsert(paymentDtoList,paymentList,customer,totalPrice);
            return paymentService.save(paymentList, customer, totalPrice);
        } catch (NullPointerException | IndexOutOfBoundsException e){
            log.error("maybe order list is null or empty");
            log.error(e.getMessage());
            return e.getMessage();
        } catch (QuantitySoldOutException e){
            log.error("Sold Out Exception : {}", e.getMessage());
            return e.getMessage();
        } catch (QuantityInsufficientException e){
            log.error("Quantity Insufficient Exception : {}", e.getMessage());
            return e.getMessage();
        }
    }


    private void paymentConverterForInsert(List<PaymentDto> paymentDtoList, List<Payment> paymentList, Customer customer, Long[] totalPrice) throws RuntimeException{
        Map<Long, Size> sizeMap = new HashMap<>();
        for(PaymentDto paymentDto : paymentDtoList){
            sizeService.putEntityToEntityMap(sizeMap, paymentDto.getSizeId(), paymentDto.getProductCount());
            totalPrice[0] += (long)paymentDto.getProductPrice();
            paymentList.add(new Payment(null, customer, sizeMap.get(paymentDto.getSizeId()).getProduct(), sizeMap.get(paymentDto.getSizeId()), paymentDto.getProductCount(), paymentDto.getProductPrice()));
        }
    }

}
