package com.bestcommerce.product.service;

import com.bestcommerce.product.entity.Brand;
import com.bestcommerce.product.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public Brand getBrand(Long id){
        return brandRepository.findById(id).orElseThrow(()-> new NullPointerException("brand not exists"));
    }
}
