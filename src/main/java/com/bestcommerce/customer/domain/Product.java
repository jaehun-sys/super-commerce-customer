package com.bestcommerce.customer.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "product")
@Data
public class Product {

    @Id
    private Long product_id;
    private String product_nm;
    private int product_cost;
    private String info;

    private Long seller_id;

    private String thumb_path;
    private int delivery_cost;
}
