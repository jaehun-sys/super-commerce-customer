package com.bestcommerce.payment.controller;

import com.bestcommerce.customer.entity.Customer;
import com.bestcommerce.payment.dto.OrderItemDto;
import com.bestcommerce.payment.dto.PaymentLogDto;
import com.bestcommerce.payment.entity.Payment;
import com.bestcommerce.payment.dto.PaymentDto;
import com.bestcommerce.payment.service.PaymentService;
import com.bestcommerce.size.entity.Size;
import com.bestcommerce.util.DtoList;
import com.bestcommerce.util.converter.EntityConverter;
import com.bestcommerce.util.exception.QuantityInsufficientException;
import com.bestcommerce.util.exception.QuantitySoldOutException;
import com.bestcommerce.customer.service.CustomerService;
import com.bestcommerce.size.service.SizeService;
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

    private final EntityConverter entityConverter;

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

    @PostMapping("/get/recent")
    public List<OrderItemDto> getPaymentList(@RequestBody PaymentLogDto paymentLogDto){
        return paymentService.getOrderList(entityConverter.toPaymentLog(paymentLogDto, customerService.getOneCustomerInfo(paymentLogDto.getCustomerId())));
    }


    private void paymentConverterForInsert(List<PaymentDto> paymentDtoList, List<Payment> paymentList, Customer customer, Long[] totalPrice) throws RuntimeException{
        Map<Long, Size> sizeMap = new HashMap<>();
        for(PaymentDto paymentDto : paymentDtoList){
            sizeService.putEntityToEntityMap(sizeMap, paymentDto.getSizeId(), paymentDto.getProductCount());
            totalPrice[0] += (long)sizeMap.get(paymentDto.getSizeId()).getProduct().getProductCost();
            paymentList.add(new Payment(null, customer, sizeMap.get(paymentDto.getSizeId()).getProduct(), sizeMap.get(paymentDto.getSizeId()), paymentDto.getProductCount(), sizeMap.get(paymentDto.getSizeId()).getProduct().getProductCost()));
        }
    }

}
