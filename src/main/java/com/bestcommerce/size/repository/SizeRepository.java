package com.bestcommerce.size.repository;

import com.bestcommerce.size.dto.SizeDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.bestcommerce.product.entity.QProduct.product;
import static com.bestcommerce.size.entity.QQuantity.quantity;
import static com.bestcommerce.size.entity.QSize.size;

@Repository
public class SizeRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public SizeRepository(JPAQueryFactory queryFactory){
        super(SizeDto.class);
        this.queryFactory = queryFactory;
    }

    public List<SizeDto> getSizeInfoForOneProduct(Long productId){
        return queryFactory.select(Projections.constructor(SizeDto.class,
                    size.sizeId.as("sizeId"),
                    product.productId.as("productId"),
                    quantity.id.as("quantityId"),
                    quantity.name.as("quantityName"),
                    size.bodyId.as("bodyId"),
                    size.bodyName.as("bodyName"),
                    size.sizeValue.as("sizeValue"),
                    quantity.remain.as("remain")
                ))
                .from(size)
                .leftJoin(size.quantity, quantity)
                .leftJoin(quantity.product, product)
                .on(product.productId.eq(productId)).fetch();
    }
}
