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

    public Product getOnlyOneProduct(Long product_id){
        return productRepository.findById(product_id).orElseThrow(NullPointerException::new);
    }

    public ProductDto getDetailProduct(Long customerId, Long productId){
        return productRepositorySupport.getDetailProduct(customerId, productId);
    }

    public List<ProductDto> getSearchProducts(Long customerId, String searchValue){
        return productRepositorySupport.getSearchProducts(customerId,searchValue);
    }

    public List<ProductDto> getLikeProductList(Long customerId){
        return productRepositorySupport.getLikeProductList(customerId);
    }
}
