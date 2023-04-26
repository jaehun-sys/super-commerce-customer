package com.bestcommerce.payment.entity;


import com.bestcommerce.customer.entity.Customer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "payment_log")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentLog {

    @Id
    @Column(name = "pay_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payNo;

    @Column(name = "total_price")
    private Long totalPrice;

    @Column(name = "pay_date")
    private String payDate;

    @ManyToOne
    @JoinColumn(name = "cu_id")
    private Customer customer;

    public PaymentLog(Long payNo,Long totalPrice, String payDate, Customer customer) {
        this.payNo = payNo;
        this.totalPrice = totalPrice;
        this.payDate = payDate;
        this.customer = customer;
    }
}