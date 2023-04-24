package com.bestcommerce.payment;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentDto {

    private Long paymentId;

    private Long paymentLogId;

    private Long customerId;

    private Long productId;

    private Long sizeId;

    private int productCount;

    private int productPrice;

    public PaymentDto(Long paymentId, Long paymentLogId, Long customerId, Long productId, Long sizeId, int productCount, int productPrice) {
        this.paymentId = paymentId;
        this.paymentLogId = paymentLogId;
        this.customerId = customerId;
        this.productId = productId;
        this.sizeId = sizeId;
        this.productCount = productCount;
        this.productPrice = productPrice;
    }
}
