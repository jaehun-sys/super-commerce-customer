package com.bestcommerce.cart.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartKeyDto {

    private Long customerId;

    private Long quantityId;

    public CartKeyDto(Long customerId, Long quantityId) {
        this.customerId = customerId;
        this.quantityId = quantityId;
    }
}
