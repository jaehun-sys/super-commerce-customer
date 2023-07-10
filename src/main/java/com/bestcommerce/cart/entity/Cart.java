package com.bestcommerce.cart.entity;

import com.bestcommerce.customer.entity.Customer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity(name = "cart")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart{

    @EmbeddedId
    private CartKey cartKey = new CartKey();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cu_id")
    @MapsId("customerId")
    private Customer customer;

    @Column(name = "product_cnt")
    private int productCount;

    public Cart(CartKey cartKey, int productCount){
        this.cartKey = cartKey;
        this.productCount = productCount;
    }
}
