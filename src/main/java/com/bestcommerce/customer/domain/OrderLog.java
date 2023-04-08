package com.bestcommerce.customer.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "order_log")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderLog {

    @Id
    @Column(name = "order_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "total_price")
    private Long totalPrice;

    @Column(name = "order_date")
    private String orderDate;

    @ManyToOne
    @JoinColumn(name = "cu_id")
    private Customer customer;

    public OrderLog(Long totalPrice, String orderDate, Customer customer) {
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.customer = customer;
    }
}
