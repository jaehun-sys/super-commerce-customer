package com.bestcommerce.customer.dto;

import lombok.Getter;

@Getter
public class CartKeyDto {

    private Long customerId;

    private Long productId;

    private Long sizeId;

    public CartKeyDto(Long customerId, Long productId, Long sizeId) {
        this.customerId = customerId;
        this.productId = productId;
        this.sizeId = sizeId;
    }
}
