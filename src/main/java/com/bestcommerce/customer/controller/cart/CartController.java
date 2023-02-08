package com.bestcommerce.customer.controller.cart;

import com.bestcommerce.customer.dto.CartDto;
import com.bestcommerce.customer.service.cart.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @PostMapping("/put")
    public void putProductToCart(@RequestBody CartDto cartDto){
        cartService.putProductToCart(cartDto);
    }
}
