package com.bestcommerce.customer.product;

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
        return productRepository.findById(product_id).orElseGet(Product::new);
    }


}
