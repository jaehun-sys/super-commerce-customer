package com.bestcommerce.product.service;

import com.bestcommerce.product.entity.Product;
import com.bestcommerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSelectService {
    private final ProductRepository productRepository;

    public List<Product> getAllProductList(){
        return productRepository.findAll();
    }

    public Product getOnlyOneProduct(Long product_id){
        return productRepository.findById(product_id).orElseThrow(NullPointerException::new);
    }

    public List<Product> searchProducts(String searchValue){
        return productRepository.findAllByProductNameContainingOrInfoContaining(searchValue,searchValue);
    }
}
