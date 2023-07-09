package com.bestcommerce.product.dto;

import lombok.Getter;

@Getter
public class ProductDetailDto {

    private ProductDto productDto;

    public ProductDetailDto(ProductDto productDto) {
        this.productDto = productDto;
    }
}
