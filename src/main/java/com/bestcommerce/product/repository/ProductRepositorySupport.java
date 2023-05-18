package com.bestcommerce.product.repository;

import com.bestcommerce.product.dto.ProductDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.bestcommerce.product.entity.QBrand.brand;
import static com.bestcommerce.product.entity.QProduct.product;

@Repository
public class ProductRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public ProductRepositorySupport(JPAQueryFactory queryFactory) {
        super(ProductDto.class);
        this.queryFactory = queryFactory;
    }

    public ProductDto getDetailProduct(Long productId){
        return getInitialJPAQuery()
                .where(product.productId.eq(productId)).fetchOne();
    }

    public List<ProductDto> getSearchProducts(String searchValue){
        return getInitialJPAQuery()
                .where(product.productName.eq(searchValue).or(product.info.eq(searchValue))).fetch();
    }

    private JPAQuery<ProductDto> getInitialJPAQuery(){
        return queryFactory.select(Projections.constructor(ProductDto.class,
                        product.productId.as("productId"),
                        brand.id.as("brandId"),
                        brand.name.as("brandName"),
                        product.productName.as("productName"),
                        product.productCost.as("productCost"),
                        product.info.as("info"),
                        product.thumbPath.as("thumbPath"),
                        product.deliveryCost.as("deliveryCost")
                ))
                .from(product)
                .innerJoin(product.brand, brand);
    }
}
