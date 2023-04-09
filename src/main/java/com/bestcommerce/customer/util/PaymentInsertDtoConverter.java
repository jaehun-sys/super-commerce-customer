package com.bestcommerce.customer.util;

import com.bestcommerce.customer.domain.*;
import com.bestcommerce.customer.dto.PaymentDto;
import com.bestcommerce.customer.service.customer.CustomerService;
import com.bestcommerce.customer.service.product.ProductSelectService;
import com.bestcommerce.customer.service.size.SizeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PaymentInsertDtoConverter {

    private static final Logger log = LoggerFactory.getLogger(PaymentInsertDtoConverter.class);

    private final CustomerService customerService;

    private final ProductSelectService productSelectService;

    private final SizeService sizeService;

    public PaymentInsertDtoConverter(CustomerService customerService, ProductSelectService productSelectService, SizeService sizeService) {
        this.customerService = customerService;
        this.productSelectService = productSelectService;
        this.sizeService = sizeService;
    }

    public void paymentConverterForInsert(List<PaymentDto> paymentDtoList, List<Payment> paymentList, PaymentLog paymentLog){
        try {
            Map<Long, Customer> customerMap = new HashMap<>();
            Map<Long, Product> productMap = new HashMap<>();
            Map<Long, Size> sizeMap = new HashMap<>();
            for(PaymentDto paymentDto : paymentDtoList){
                putEntityToEntityMap(customerMap,productMap,sizeMap, paymentDto);
                paymentLog.addTotalPriceForPaymentInsert((long)paymentDto.getProductPrice());
                paymentList.add(new Payment(null, customerMap.get(paymentDto.getCustomerId()), productMap.get(paymentDto.getProductId()), sizeMap.get(paymentDto.getSizeId()), paymentDto.getProductCount(), paymentDto.getProductPrice()));
            }

            paymentLog.setCustomer(customerMap.get(paymentDtoList.get(0).getCustomerId()));
        } catch (NullPointerException | IndexOutOfBoundsException e){
            log.error("orderDtoList is Null Or Empty .. {}", e.getMessage());
            paymentLog.setCustomer(null);
            log.info("set order Log customer_id null");
        }
    }

    private void putEntityToEntityMap(Map<Long, Customer> customerMap, Map<Long, Product> productMap, Map<Long, Size> sizeMap, PaymentDto paymentDto){
        if(!customerMap.containsKey(paymentDto.getCustomerId())){
            customerMap.put(paymentDto.getCustomerId(), customerService.getOneCustomerInfo(paymentDto.getCustomerId()));
        }
        if(!productMap.containsKey(paymentDto.getProductId())){
            productMap.put(paymentDto.getProductId(), productSelectService.getOnlyOneProduct(paymentDto.getProductId()));
        }
        if(!sizeMap.containsKey(paymentDto.getSizeId())){
            sizeMap.put(paymentDto.getSizeId(), sizeService.getOneSizeInfo(paymentDto.getSizeId()));
        }
    }
}
