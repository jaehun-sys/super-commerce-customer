package com.bestcommerce.customer.dto;

import lombok.Getter;

@Getter
public class PaymentLogDto {

    private Long payNo;

    private Long customerId;

    private Long totalPrice;

    private String orderDate;

    public PaymentLogDto(Long payNo, Long customerId, Long totalPrice, String orderDate) {
        this.payNo = payNo;
        this.customerId = customerId;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }
}
