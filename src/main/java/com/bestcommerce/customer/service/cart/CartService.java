package com.bestcommerce.customer.service.cart;

import com.bestcommerce.customer.domain.*;
import com.bestcommerce.customer.repository.domain.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    public void putProductToCart(Size size, Customer customer, Product product, int productCount){
        CartKey cartKey = new CartKey(customer.getCuId(), product.getProductId(), size.getSizeId());
        boolean isExistInTable = cartRepository.existsById(cartKey);
        if(isExistInTable){
            cartRepository.increaseProductCountByCartKey(cartKey.getCustomerId(), cartKey.getProductId(), cartKey.getSizeId(), productCount);
            return;
        }
        cartRepository.save(new Cart(productCount, size, customer, product));
    }
}
