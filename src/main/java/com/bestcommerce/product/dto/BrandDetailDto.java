package com.bestcommerce.product.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class BrandDetailDto {

    private BrandDto brandDto;

    private List<ProductDto> productList;

    public BrandDetailDto(BrandDto brandDto, List<ProductDto> productList) {
        this.brandDto = brandDto;
        this.productList = productList;
    }
}
