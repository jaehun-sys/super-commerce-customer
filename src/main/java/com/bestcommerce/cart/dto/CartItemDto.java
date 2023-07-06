package com.bestcommerce.cart.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartItemDto {
    private Long customerId;

    private String customerName;

    private Long productId;

    private String productName;

    private int productCost;

    private Long brandId;

    private String brandName;

    private String thumbnailPath;

    private int deliveryCost;

    private Long quantityId;

    private String quantityName;

    public CartItemDto(Long customerId, String customerName, Long productId, String productName, int productCost, Long brandId, String brandName, String thumbnailPath, int deliveryCost, Long quantityId, String quantityName) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.productId = productId;
        this.productName = productName;
        this.productCost = productCost;
        this.brandId = brandId;
        this.brandName = brandName;
        this.thumbnailPath = thumbnailPath;
        this.deliveryCost = deliveryCost;
        this.quantityId = quantityId;
        this.quantityName = quantityName;
    }

}
