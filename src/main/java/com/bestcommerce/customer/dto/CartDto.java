package com.bestcommerce.customer.dto;

import lombok.Getter;

@Getter
public class CartDto {
    private Long cartId;

    private int productCount;

    private Long sizeId;

    private Long customerId;

    private Long productId;

    public CartDto(int productCount, Long sizeId, Long customerId, Long productId) {
        this.productCount = productCount;
        this.sizeId = sizeId;
        this.customerId = customerId;
        this.productId = productId;
    }
}
