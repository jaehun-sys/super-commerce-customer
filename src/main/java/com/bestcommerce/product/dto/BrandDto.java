package com.bestcommerce.product.dto;

import lombok.Getter;

@Getter
public class BrandDto {

    private Long brandId;

    private String brandName;

    private String brandIntro;

    private String like;

    public BrandDto(Long brandId, String brandName, String brandIntro, String like) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.brandIntro = brandIntro;
        this.like = like;
    }
}
