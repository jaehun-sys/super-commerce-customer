package com.bestcommerce.customer.service.product;

import com.bestcommerce.customer.domain.Product;
import com.bestcommerce.customer.repository.domain.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSelectService {
    private final ProductRepository productRepository;

    public ProductSelectService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProductList(){
        return productRepository.findAll();
    }

    public Product getOnlyOneProduct(Long product_id){

        return productRepository.findById(product_id).get();
    }

}
