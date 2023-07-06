package com.bestcommerce.payment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentDto {

    private Long paymentId;

    private Long paymentLogId;

    private Long customerId;

    private Long quantityId;

    private int productCount;

    private int paymentPrice;

    public PaymentDto(Long paymentId, Long paymentLogId, Long customerId, Long quantityId, int productCount, int paymentPrice) {
        this.paymentId = paymentId;
        this.paymentLogId = paymentLogId;
        this.customerId = customerId;
        this.quantityId = quantityId;
        this.productCount = productCount;
        this.paymentPrice = paymentPrice;
    }
}
