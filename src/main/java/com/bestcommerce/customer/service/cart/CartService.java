package com.bestcommerce.customer.service.cart;

import com.bestcommerce.customer.domain.*;
import com.bestcommerce.customer.dto.CartItemDto;
import com.bestcommerce.customer.repository.domain.CartRepository;
import com.bestcommerce.customer.repository.support.CartRepositorySupport;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CartService {

    private final CartRepository cartRepository;

    private final CartRepositorySupport cartRepositorySupport;

    public CartService(CartRepository cartRepository, CartRepositorySupport cartRepositorySupport){
        this.cartRepository = cartRepository;
        this.cartRepositorySupport = cartRepositorySupport;
    }

    public void putProductToCart(Size size, Customer customer, Product product, int productCount){
        CartKey cartKey = new CartKey(customer.getCuId(), product.getProductId(), size.getSizeId());
        if(cartRepository.existsById(cartKey)){
            cartRepository.increaseProductCountByCartKey(cartKey, productCount);
            return;
        }
        cartRepository.save(new Cart(productCount, size, customer, product));
    }

    public List<CartItemDto> getCartList(Long id){
        return cartRepositorySupport.getCartItemDtoList(id);
    }

    public void deleteCartList(List<CartKey> cartKeyList){
        for(CartKey cartKey : cartKeyList){
            cartRepository.deleteAllByCartKey(cartKey);
        }
    }
}
