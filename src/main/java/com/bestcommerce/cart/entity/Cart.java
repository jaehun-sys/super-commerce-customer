package com.bestcommerce.cart.entity;

import com.bestcommerce.customer.entity.Customer;
import com.bestcommerce.size.entity.Quantity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quantity_id")
    @MapsId("quantityId")
    private Quantity quantity;

    @Column(name = "product_cnt")
    private int productCount;

    public Cart(int productCount, Customer customer, Quantity quantity){
        this.productCount = productCount;
        this.customer = customer;
        this.quantity = quantity;
    }
}
