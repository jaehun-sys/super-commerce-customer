package com.bestcommerce.customer.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "cart")
public class Cart {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @Column(name = "product_cnt")
    private int productCount;

    @Column(name = "size_id")
    private Long sizeId;

    @ManyToOne
    @JoinColumn(name = "cu_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
