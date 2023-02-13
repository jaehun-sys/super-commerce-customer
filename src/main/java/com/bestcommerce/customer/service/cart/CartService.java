package com.bestcommerce.customer.service.cart;

import com.bestcommerce.customer.domain.Cart;
import com.bestcommerce.customer.domain.Customer;
import com.bestcommerce.customer.domain.Product;
import com.bestcommerce.customer.dto.CartDto;
import com.bestcommerce.customer.repository.domain.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    public void putProductToCart(Customer customer, Product product, CartDto cartDto){
        Cart cart = new Cart(cartDto.getProductCount(), cartDto.getSizeId(), customer, product);
        cartRepository.save(cart);
    }
}
