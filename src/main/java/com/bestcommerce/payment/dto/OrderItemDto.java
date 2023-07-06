package com.bestcommerce.payment.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemDto {

    private Long orderNo;

    private Long totalPrice;

    private String orderDate;

    private int productCount;

    private int productPrice;

    private Long quantityId;

    private String quantityName;

    private Long brandId;

    private String brandName;

    private Long productId;

    private String productName;

    private String thumbNail;

    public OrderItemDto(Long orderNo, Long totalPrice, String orderDate, int productCount, int productPrice, Long quantityId, String quantityName, Long brandId, String brandName, Long productId, String productName, String thumbNail) {
        this.orderNo = orderNo;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.productCount = productCount;
        this.productPrice = productPrice;
        this.quantityId = quantityId;
        this.quantityName = quantityName;
        this.brandId = brandId;
        this.brandName = brandName;
        this.productId = productId;
        this.productName = productName;
        this.thumbNail = thumbNail;
    }
}
