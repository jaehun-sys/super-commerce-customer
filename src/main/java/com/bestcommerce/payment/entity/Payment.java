package com.bestcommerce.payment.entity;

import com.bestcommerce.customer.entity.Customer;
import com.bestcommerce.size.entity.Quantity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "payment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id
    @Column(name = "pay_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pay_no")
    private PaymentLog paymentLog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cu_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quantity_id")
    private Quantity quantity;

    @Column(name = "product_cnt")
    private int productCount;

    @Column(name = "payment_price")
    private int paymentPrice;

    public Payment(PaymentLog paymentLog, Customer customer, Quantity quantity, int productCount, int paymentPrice) {
        this.paymentLog = paymentLog;
        this.customer = customer;
        this.quantity = quantity;
        this.productCount = productCount;
        this.paymentPrice = paymentPrice;
    }

}
