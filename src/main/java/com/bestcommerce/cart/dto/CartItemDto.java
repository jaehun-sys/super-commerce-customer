package com.bestcommerce.cart.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartItemDto {
    private Long customerId;

    private String customerName;

    public CartItemDto(Long customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
    }

}
