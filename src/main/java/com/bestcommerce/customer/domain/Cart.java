package com.bestcommerce.customer.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity(name = "cart")
public class Cart {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @Column(name = "product_cnt")
    private int productCount;


    @ManyToOne
    @JoinColumn(name = "cu_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Cart(){
    }

    public Cart(int productCount, Size size, Customer customer, Product product){
        this.productCount = productCount;
        this.size = size;
        this.customer = customer;
        this.product = product;
    }
}
