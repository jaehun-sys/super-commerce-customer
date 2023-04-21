package com.bestcommerce.customer.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "quantity")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Quantity {

    @EmbeddedId
    private QuantityKey quantityKey = new QuantityKey();

    @ManyToOne
    @JoinColumn(name = "product_id")
    @MapsId("productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "size_id")
    @MapsId("sizeId")
    private Size size;

    @Column(name = "count")
    private int count;

    public Quantity(Product product, Size size, int count) {
        this.product = product;
        this.size = size;
        this.count = count;
    }
}
