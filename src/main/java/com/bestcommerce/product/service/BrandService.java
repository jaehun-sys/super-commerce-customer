package com.bestcommerce.product.service;

import com.bestcommerce.product.entity.Brand;
import com.bestcommerce.product.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public Brand getBrand(Long id){
        return brandRepository.findById(id).orElseThrow(()-> new NullPointerException("brand not exists"));
    }
}
