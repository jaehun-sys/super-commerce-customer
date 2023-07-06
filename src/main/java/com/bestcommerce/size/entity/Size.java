package com.bestcommerce.size.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "size")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Size {
    @Id
    @Column(name = "size_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sizeId;

    @Column(name = "body_id")
    private Long bodyId;

    @Column(name = "body_name")
    private String bodyName;

    @Column(name = "size_value")
    private int sizeValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quantity_id")
    private Quantity quantity;

}
