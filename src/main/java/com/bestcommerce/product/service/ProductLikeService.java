package com.bestcommerce.product.service;

import com.bestcommerce.customer.entity.Customer;
import com.bestcommerce.product.entity.Product;
import com.bestcommerce.product.entity.ProductLike;
import com.bestcommerce.product.repository.ProductLikeRepository;
import com.bestcommerce.util.TimeFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductLikeService {

    private final ProductLikeRepository productLikeRepository;

    public String likeOrCancelLikeProduct(Customer customer, Product product){
        if(productLikeRepository.existsByCustomerAndProduct(customer,product)) {
            productLikeRepository.deleteByCustomerAndProduct(customer, product);
            return "DisLike";
        }
        productLikeRepository.save(new ProductLike(0L,customer,product, LocalDateTime.now().format(TimeFormat.orderLogDateFormat)));
        return "like";
    }
}
