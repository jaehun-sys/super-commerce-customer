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

    private Long brandId;

    private String brandName;

    private Long productId;

    private String productName;

    private String thumbNail;

    public OrderItemDto(Long orderNo, Long totalPrice, String orderDate, int productCount, int productPrice, Long sizeId, String sizeName, int sizeValue, Long brandId, String brandName, Long productId, String productName, String thumbNail) {
        this.orderNo = orderNo;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.productCount = productCount;
        this.productPrice = productPrice;
        this.sizeId = sizeId;
        this.sizeName = sizeName;
        this.sizeValue = sizeValue;
        this.brandId = brandId;
        this.brandName = brandName;
        this.productId = productId;
        this.productName = productName;
        this.thumbNail = thumbNail;
    }
}
