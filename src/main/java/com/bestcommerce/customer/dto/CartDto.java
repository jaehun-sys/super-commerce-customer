package com.bestcommerce.customer.dto;

import lombok.Getter;

@Getter
public class CartDto {
    private Long cartId;

    private int productCount;

    private Long sizeId;

    private Long customerId;

    private Long productId;


}
