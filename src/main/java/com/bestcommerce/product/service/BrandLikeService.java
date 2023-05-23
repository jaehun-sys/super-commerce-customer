package com.bestcommerce.product.service;

import com.bestcommerce.customer.entity.Customer;
import com.bestcommerce.product.entity.Brand;
import com.bestcommerce.product.entity.BrandLike;
import com.bestcommerce.product.repository.BrandLikeRepository;
import com.bestcommerce.util.TimeFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BrandLikeService {

    private final BrandLikeRepository brandLikeRepository;

    public String brandLikeAct(Customer customer, Brand brand){
        if(brandLikeRepository.existsByCustomerAndBrand(customer, brand)){
            brandLikeRepository.deleteByCustomerAndBrand(customer, brand);
            return "DisLike";
        }
        brandLikeRepository.save(new BrandLike(0L, customer, brand, LocalDateTime.now().format(TimeFormat.orderLogDateFormat)));
        return "like";
    }

    public String isLike(Customer customer, Brand brand){
        return brandLikeRepository.existsByCustomerAndBrand(customer, brand) ? "LIKE" : "NONE";
    }
}
