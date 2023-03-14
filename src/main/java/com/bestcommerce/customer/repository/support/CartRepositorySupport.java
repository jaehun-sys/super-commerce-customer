package com.bestcommerce.customer.repository.support;

import com.bestcommerce.customer.domain.CartKey;
import com.bestcommerce.customer.domain.QCartKey;
import com.bestcommerce.customer.dto.CartItemDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

import static com.bestcommerce.customer.domain.QCart.cart;
import static com.bestcommerce.customer.domain.QCustomer.customer;
import static com.bestcommerce.customer.domain.QProduct.product;
import static com.bestcommerce.customer.domain.QSize.size;

@Repository
public class CartRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public CartRepositorySupport(JPAQueryFactory queryFactory) {
        super(CartItemDto.class);
        this.queryFactory = queryFactory;
    }

    public List<CartItemDto> getCartItemDtoList(Long id) {
        List<CartItemDto> result =
                queryFactory.select(Projections.constructor(CartItemDto.class,
                                customer.cuId.as("customerId"),
                                customer.cuName.as("customerName"),
                                customer.cuEmail.as("customerEmail"),
                                product.productId.as("productId"),
                                product.productName.as("productName"),
                                product.productCost.as("productCost"),
                                product.sellerId.as("sellerId"),
                                product.thumbPath.as("thumbnailPath"),
                                product.deliveryCost.as("deliveryCost"),
                                size.sizeId.as("sizeId"),
                                size.measureId.as("measureId"),
                                size.measureName.as("measureName"),
                                size.contentId.as("contentId"),
                                size.contentName.as("contentName"),
                                size.sizeValue.as("sizeValue")))
                        .from(cart).innerJoin(cart.customer, customer).innerJoin(cart.product, product).innerJoin(cart.size, size)
                        .where(customer.cuId.eq(id)).fetch();

        return result;
    }

    @Transactional
    public void deleteCartList(List<CartKey> cartKeyList){
        BooleanBuilder builder = new BooleanBuilder();
        QCartKey qCartKey = QCartKey.cartKey;
        for(CartKey cartKey : cartKeyList){
            builder.and(qCartKey.eq(cartKey));
        }
        long count = queryFactory.delete(cart).where(builder).execute();
    }
}
