package com.bestcommerce.customer.controller.cart;

import com.bestcommerce.customer.service.cart.CartService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @PostMapping("/put")
    public void putProductToCart(@RequestParam("cu_id") Long cu_id
                                ,@RequestParam("product_id") Long product_id
                                ,@RequestParam("size_id") Long size_id
                                ,@RequestParam("product_cnt") int product_cnt){
        cartService.putProductToCart(cu_id, product_id, size_id, product_cnt);
    }
}
