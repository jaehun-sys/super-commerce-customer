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

    private Long sizeId;

    private String sizeName;

    private int sizeValue;

    private Long productId;

    private String productName;

    private Long sellerId;

    private String thumbNail;

    public OrderItemDto(Long orderNo, Long totalPrice, String orderDate, int productCount, int productPrice, Long sizeId, String sizeName, int sizeValue, Long productId, String productName, Long sellerId, String thumbNail) {
        this.orderNo = orderNo;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.productId = productId;
        this.productCount = productCount;
        this.productPrice = productPrice;
        this.sizeId = sizeId;
        this.sizeName = sizeName;
        this.sizeValue = sizeValue;
        this.productName = productName;
        this.sellerId = sellerId;
        this.thumbNail = thumbNail;
    }
}
