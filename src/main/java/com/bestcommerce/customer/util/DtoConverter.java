package com.bestcommerce.customer.util;

import com.bestcommerce.customer.domain.Product;
import com.bestcommerce.customer.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DtoConverter {

    public ProductDto toProductDto(Product product){
        return new ProductDto(product.getProductId(), product.getProductName(), product.getProductCost(), product.getInfo(), product.getThumbPath(), product.getDeliveryCost());
    }

    public List<ProductDto> toProductDtoList(List<Product> productList){
        List<ProductDto> productDtoList = new ArrayList<>();
        for(Product product : productList){
            productDtoList.add(toProductDto(product));
        }
        return productDtoList;
    }
}
