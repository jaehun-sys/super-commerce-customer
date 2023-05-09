package com.bestcommerce.product.dto;

import com.bestcommerce.size.dto.SizeDto;
import lombok.Getter;

import java.util.List;

@Getter
public class ProductDetailDto {

    private ProductDto productDto;

    private List<SizeDto> sizeDtoList;

    public ProductDetailDto(ProductDto productDto, List<SizeDto> sizeDtoList) {
        this.productDto = productDto;
        this.sizeDtoList = sizeDtoList;
    }
}
