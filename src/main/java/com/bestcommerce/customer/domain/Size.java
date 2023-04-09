package com.bestcommerce.customer.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity(name = "size")
public class Size {
    @Id
    @Column(name = "size_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sizeId;

    @Column(name = "measure_id")
    private Long measureId;

    @Column(name = "measure_nm")
    private String measureName;

    @Column(name = "content_id")
    private Long contentId;

    @Column(name = "content_nm")
    private String contentName;

    @Column(name = "size_value")
    private int sizeValue;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "size")
    private List<Cart> cartList = new ArrayList<>();

    @OneToMany(mappedBy = "size")
    private List<Payment> paymentList = new ArrayList<>();

    public Size(Long sizeId, Long measureId, String measureName, Long contentId, String contentName, int sizeValue, Product product) {
        this.sizeId = sizeId;
        this.measureId = measureId;
        this.measureName = measureName;
        this.contentId = contentId;
        this.contentName = contentName;
        this.sizeValue = sizeValue;
        this.product = product;
    }

    public Size() {

    }
}
