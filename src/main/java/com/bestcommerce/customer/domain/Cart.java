package com.bestcommerce.customer.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity(name = "cart")
public class Cart{

    @EmbeddedId
    private CartKey cartKey = new CartKey();

    @ManyToOne
    @JoinColumn(name = "cu_id")
    @MapsId("customerId")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @MapsId("productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "size_id")
    @MapsId("sizeId")
    private Size size;

    @Column(name = "product_cnt")
    private int productCount;

    public Cart(){
    }

    public Cart(int productCount, Size size, Customer customer, Product product){
        this.productCount = productCount;
        this.size = size;
        this.customer = customer;
        this.product = product;
    }
}
