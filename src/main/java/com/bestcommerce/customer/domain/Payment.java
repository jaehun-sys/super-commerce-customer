package com.bestcommerce.customer.domain;

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

    @ManyToOne
    @JoinColumn(name = "pay_no")
    private PaymentLog paymentLog;

    @ManyToOne
    @JoinColumn(name = "cu_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;

    @Column(name = "product_cnt")
    private int productCount;

    @Column(name = "product_price")
    private int productPrice;

    public Payment(PaymentLog paymentLog, Customer customer, Product product, Size size, int productCount, int productPrice) {
        this.paymentLog = paymentLog;
        this.customer = customer;
        this.product = product;
        this.size = size;
        this.productCount = productCount;
        this.productPrice = productPrice;
    }

}
