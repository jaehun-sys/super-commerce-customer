package com.bestcommerce.cart.service;

import com.bestcommerce.cart.entity.Cart;
import com.bestcommerce.cart.dto.CartItemDto;
import com.bestcommerce.cart.entity.CartKey;
import com.bestcommerce.cart.repository.CartRepository;
import com.bestcommerce.cart.repository.CartRepositorySupport;
import com.bestcommerce.customer.entity.Customer;
import com.bestcommerce.size.entity.Quantity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    private final CartRepositorySupport cartRepositorySupport;

    public void putProductToCart(Customer customer, Quantity quantity, int productCount){
        CartKey cartKey = new CartKey(customer.getCuId(), quantity.getId());
        if(cartRepository.existsById(cartKey)){
            cartRepository.increaseProductCountByCartKey(cartKey, productCount);
            return;
        }
        cartRepository.save(new Cart(productCount, customer, quantity));
    }

    public List<CartItemDto> getCartList(Long id){
        return cartRepositorySupport.getCartItemDtoList(id);
    }

    public void deleteCartList(List<CartKey> cartKeyList){
        cartRepositorySupport.deleteCartList(cartKeyList);
    }
}
