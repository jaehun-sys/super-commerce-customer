package com.bestcommerce.product.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductDto {
    private Long productId;

    private String productName;

    private int productCost;

    private String info;

    private String thumbPath;

    private int deliveryCost;

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
