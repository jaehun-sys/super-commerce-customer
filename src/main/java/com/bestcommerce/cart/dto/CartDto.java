package com.bestcommerce.cart.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartDto {

    private int productCount;

    private Long customerId;

    private Long quantityId;

    public CartDto(int productCount, Long customerId, Long quantityId) {
        this.productCount = productCount;
        this.customerId = customerId;
        this.quantityId = quantityId;
    }
}
