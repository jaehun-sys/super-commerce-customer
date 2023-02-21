package com.bestcommerce.customer.service.cart;

import com.bestcommerce.customer.domain.Cart;
import com.bestcommerce.customer.domain.Customer;
import com.bestcommerce.customer.domain.Product;
import com.bestcommerce.customer.domain.Size;
import com.bestcommerce.customer.repository.domain.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    public void putProductToCart(Size size, Customer customer, Product product, int productCount){
        cartRepository.save(new Cart(productCount, size, customer, product));
    }
}
