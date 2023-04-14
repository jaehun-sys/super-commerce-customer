package com.bestcommerce.customer.service.product;

import com.bestcommerce.customer.domain.Product;
import com.bestcommerce.customer.repository.domain.ProductRepository;
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
