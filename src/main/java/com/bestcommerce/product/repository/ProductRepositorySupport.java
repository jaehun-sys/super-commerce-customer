package com.bestcommerce.product.repository;

import com.bestcommerce.product.dto.ProductDto;
import com.bestcommerce.product.entity.QProduct;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.bestcommerce.product.entity.QBrand.brand;
import static com.bestcommerce.product.entity.QProduct.product;
import static com.bestcommerce.product.entity.QProductLike.productLike;

@Repository
public class ProductRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public ProductRepositorySupport(JPAQueryFactory queryFactory) {
        super(ProductDto.class);
        this.queryFactory = queryFactory;
    }

    public ProductDto getDetailProduct(Long customerId, Long productId){
        return getInitialJPAQuery(customerId)
                .where(product.productId.eq(productId)).fetchOne();
    }

    public List<ProductDto> getSearchProducts(Long customerId, String searchValue){
        return getInitialJPAQuery(customerId)
                .where(product.productName.contains(searchValue).or(product.info.contains(searchValue))).fetch();
    }

    public List<ProductDto> getLikeProductList(Long customerId){
        return queryFactory.select(Projections.constructor(ProductDto.class,
                        product.productId.as("productId"),
                        brand.id.as("brandId"),
                        brand.name.as("brandName"),
                        product.productName.as("productName"),
                        product.productCost.as("productCost"),
                        product.info.as("info"),
                        product.thumbPath.as("thumbPath"),
                        product.deliveryCost.as("deliveryCost"),
                        productLike.likeDate.as("productLike")
                ))
                .from(productLike)
                .innerJoin(productLike.product, product)
                .innerJoin(product.brand, brand)
                .where(productLike.customer.cuId.eq(customerId))
                .orderBy(productLike.likeDate.asc())
                .fetch();
    }

    private JPAQuery<ProductDto> getInitialJPAQuery(Long customerId){
        return queryFactory.select(Projections.constructor(ProductDto.class,
                        product.productId.as("productId"),
                        brand.id.as("brandId"),
                        brand.name.as("brandName"),
                        product.productName.as("productName"),
                        product.productCost.as("productCost"),
                        product.info.as("info"),
                        product.thumbPath.as("thumbPath"),
                        product.deliveryCost.as("deliveryCost"),
                        productLike.likeDate.coalesce("notLike").as("productLike")
                ))
                .from(product)
                .innerJoin(product.brand, brand)
                .leftJoin(productLike)
                .on(productLike.product.productId.eq(product.productId).and(productLike.customer.cuId.eq(customerId)));
    }
}
