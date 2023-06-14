package com.bestcommerce.size.dto;

import lombok.Getter;

@Getter
public class SizeDto {

    private Long sizeId;

    private Long productId;

    private Long quantityId;

    private String quantityName;

    private Long bodyId;

    private String bodyName;

    private int sizeValue;

    private int remain;

    public SizeDto(Long sizeId, Long productId, Long quantityId, String quantityName, Long bodyId, String bodyName, int sizeValue, int remain) {
        this.sizeId = sizeId;
        this.productId = productId;
        this.quantityId = quantityId;
        this.quantityName = quantityName;
        this.bodyId = bodyId;
        this.bodyName = bodyName;
        this.sizeValue = sizeValue;
        this.remain = remain;
    }
}
