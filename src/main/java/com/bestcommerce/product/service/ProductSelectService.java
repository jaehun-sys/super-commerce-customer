package com.bestcommerce.product.service;

import com.bestcommerce.product.dto.ProductDto;
import com.bestcommerce.product.entity.Product;
import com.bestcommerce.product.repository.ProductRepository;
import com.bestcommerce.product.repository.ProductRepositorySupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSelectService {
    private final ProductRepository productRepository;

    private final ProductRepositorySupport productRepositorySupport;

    public List<Product> getAllProductList(){
        return productRepository.findAll();
    }

    public Product getOnlyOneProduct(Long product_id){
        return productRepository.findById(product_id).orElseThrow(NullPointerException::new);
    }

    public ProductDto getDetailProduct(Long productId){
        return productRepositorySupport.getDetailProduct(productId);
    }

    public List<ProductDto> getSearchProducts(String searchValue){
        return productRepositorySupport.getSearchProducts(searchValue);
    }
}
