package com.bestcommerce.customer.service.payment;

import com.bestcommerce.customer.domain.*;
import com.bestcommerce.customer.dto.PaymentDto;
import com.bestcommerce.customer.exception.QuantityException;
import com.bestcommerce.customer.repository.domain.CustomerRepository;
import com.bestcommerce.customer.repository.domain.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;

@Component
@RequiredArgsConstructor
public class PaymentInsertDtoConvert {

    private final CustomerRepository customerRepository;

    private final SizeRepository sizeRepository;

    public void paymentConverterForInsert(List<PaymentDto> paymentDtoList, List<Payment> paymentList, PaymentLog paymentLog) throws RuntimeException{
        Map<Long, Product> productMap = new HashMap<>();
        Map<Long, Size> sizeMap = new HashMap<>();
        Customer customer = customerRepository.findById(paymentDtoList.get(0).getCustomerId()).orElseThrow(NullPointerException::new);
        for(PaymentDto paymentDto : paymentDtoList){
            putEntityToEntityMap(productMap,sizeMap, paymentDto);
            paymentLog.addTotalPriceForPaymentInsert((long)paymentDto.getProductPrice());
            paymentList.add(new Payment(null, customer, productMap.get(paymentDto.getProductId()), sizeMap.get(paymentDto.getSizeId()), paymentDto.getProductCount(), paymentDto.getProductPrice()));
        }
        paymentLog.setCustomer(customer);
    }

    @Transactional
    void putEntityToEntityMap(Map<Long, Product> productMap, Map<Long, Size> sizeMap, PaymentDto paymentDto) throws QuantityException {
        if(sizeMap.containsKey(paymentDto.getSizeId())){
            return;
        }
        Size size = sizeRepository.findWithIdForUpdate(paymentDto.getSizeId()).orElseThrow(NullPointerException::new);
        size.orderSize(paymentDto.getProductCount());
        sizeRepository.save(size);
        sizeMap.put(size.getSizeId(), size);
        productMap.put(size.getProduct().getProductId(), size.getProduct());
    }
}
