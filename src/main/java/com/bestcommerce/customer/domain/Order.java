package com.bestcommerce.customer.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "order")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "order_no")
    private OrderLog orderLog;

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

    public Order(OrderLog orderLog, Customer customer, Product product, Size size, int productCount, int productPrice) {
        this.orderLog = orderLog;
        this.customer = customer;
        this.product = product;
        this.size = size;
        this.productCount = productCount;
        this.productPrice = productPrice;
    }

}
