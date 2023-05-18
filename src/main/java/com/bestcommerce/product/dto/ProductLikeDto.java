package com.bestcommerce.product.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductLikeDto {

    private Long customerId;

    private Long productId;

    public ProductLikeDto(Long customerId, Long productId) {
        this.customerId = customerId;
        this.productId = productId;
    }
}
