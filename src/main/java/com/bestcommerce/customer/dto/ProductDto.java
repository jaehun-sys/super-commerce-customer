package com.bestcommerce.customer.dto;

import lombok.Getter;

@Getter
public class ProductDto {
    private final Long productId;

    private final String productName;

    private final int productCost;

    private final String info;

    private final String thumbPath;

    private final int deliveryCost;

    public ProductDto(Long productId,
                      String productName,
                      int productCost,
                      String info,
                      String thumbPath,
                      int deliveryCost){
        this.productId = productId;
        this.productName = productName;
        this.productCost = productCost;
        this.info = info;
        this.thumbPath = thumbPath;
        this.deliveryCost = deliveryCost;
    }
}
