package com.bestcommerce.cart.entity;

import com.bestcommerce.customer.entity.Customer;
import com.bestcommerce.product.entity.Product;
import com.bestcommerce.size.entity.Size;
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
    @JoinColumn(name = "product_id")
    @MapsId("productId")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size_id")
    @MapsId("sizeId")
    private Size size;

    @Column(name = "product_cnt")
    private int productCount;

    public Cart(int productCount, Size size, Customer customer, Product product){
        this.productCount = productCount;
        this.size = size;
        this.customer = customer;
        this.product = product;
    }
}
