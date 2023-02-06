package com.bestcommerce.customer.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "product")
@Data
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "product_nm")
    private String productName;

    @Column(name = "product_cost")
    private int productCost;

    @Column(name = "info")
    private String info;

    @Column(name = "seller_id")
    private Long sellerId;

    @Column(name = "thumb_path")
    private String thumbPath;

    @Column(name = "delivery_cost")
    private int deliveryCost;

    @OneToMany(mappedBy = "product")
    private List<Cart> cartsList = new ArrayList<>();
}
