package com.bestcommerce.size.service;

import com.bestcommerce.size.entity.Quantity;
import com.bestcommerce.size.repository.QuantityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuantityService {

    private final QuantityRepository quantityRepository;

    @Transactional
    public Quantity getQuantityForPayment(Long id, int orderCount){
        Quantity quantity = quantityRepository.findWithIdForUpdate(id).orElseThrow(()-> new RuntimeException("수량에 대한 잘못된 값"));
        quantity.orderSize(orderCount);
        quantityRepository.save(quantity);
        return quantity;
    }

    public Quantity getQuantityForCart(Long id){
        return quantityRepository.findById(id).orElseThrow(()-> new RuntimeException("수량에 대한 잘못된 값"));
    }
}
