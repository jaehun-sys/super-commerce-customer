package com.bestcommerce.customer.service.cart;

import com.bestcommerce.customer.domain.*;
import com.bestcommerce.customer.dto.CartItemDto;
import com.bestcommerce.customer.repository.domain.CartRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

import static com.bestcommerce.customer.domain.QCart.cart;
import static com.bestcommerce.customer.domain.QCustomer.customer;
import static com.bestcommerce.customer.domain.QProduct.product;
import static com.bestcommerce.customer.domain.QSize.size;

@Service
public class CartService {

    private final CartRepository cartRepository;

    private final EntityManager entityManager;
    private JPAQueryFactory queryFactory;

    public CartService(CartRepository cartRepository, EntityManager entityManager){
        this.cartRepository = cartRepository;
        this.entityManager = entityManager;
    }

    public void putProductToCart(Size size, Customer customer, Product product, int productCount){
        CartKey cartKey = new CartKey(customer.getCuId(), product.getProductId(), size.getSizeId());
        if(cartRepository.existsById(cartKey)){
            cartRepository.increaseProductCountByCartKey(cartKey, productCount);
            return;
        }
        cartRepository.save(new Cart(productCount, size, customer, product));
    }

    public List<CartItemDto> practice(){
        queryFactory = new JPAQueryFactory(entityManager);

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
                .where(customer.cuId.eq(1L)).fetch();

        return result;
    }
}
