package com.bestcommerce.size.dto;

import lombok.Getter;

@Getter
public class SizeDto {

    private Long sizeId;

    private Long productId;

    private Long measureId;

    private String measureName;

    private Long contentId;

    private String contentName;

    private int sizeValue;

    private int sizeRemainQuantity;

    public SizeDto(Long sizeId, Long productId, Long measureId, String measureName, Long contentId, String contentName, int sizeValue, int sizeRemainQuantity) {
        this.sizeId = sizeId;
        this.productId = productId;
        this.measureId = measureId;
        this.measureName = measureName;
        this.contentId = contentId;
        this.contentName = contentName;
        this.sizeValue = sizeValue;
        this.sizeRemainQuantity = sizeRemainQuantity;
    }
}
