package com.bestcommerce.cart.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
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
