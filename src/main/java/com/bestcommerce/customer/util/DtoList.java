package com.bestcommerce.customer.util;

import com.bestcommerce.customer.payment.PaymentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DtoList {
    private List<PaymentDto> orderList;

    public DtoList(List<PaymentDto> orderList) {
        this.orderList = orderList;
    }
}
