package com.bestcommerce.product.entity;

import com.bestcommerce.customer.entity.Customer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "product_like")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cu_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "date")
    private String likeDate;

    public ProductLike(Long id, Customer customer, Product product, String likeDate) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.likeDate = likeDate;
    }
}
