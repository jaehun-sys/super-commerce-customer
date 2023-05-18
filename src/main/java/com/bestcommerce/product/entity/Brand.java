package com.bestcommerce.product.entity;

import com.bestcommerce.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "brand")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Brand {

    @Id
    @Column(name = "brand_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "intro")
    private String intro;

    @Column(name = "business_address")
    private String address;

    @Column(name = "regdate")
    private String registerDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "brand")
    private List<Product> productList = new ArrayList<>();
}
