package com.bestcommerce.customer.controller.cart;

import com.bestcommerce.customer.dto.CartDto;
import com.bestcommerce.customer.service.account.AccountService;
import com.bestcommerce.customer.service.cart.CartService;
import com.bestcommerce.customer.service.product.ProductSelectService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    private final ProductSelectService productSelectService;

    private final AccountService accountService;

    public CartController(CartService cartService, ProductSelectService productSelectService, AccountService accountService){
        this.cartService = cartService;
        this.productSelectService = productSelectService;
        this.accountService = accountService;
    }

    @PostMapping("/put")
    public void putProductToCart(@RequestBody CartDto cartDto){
        cartService.putProductToCart(accountService.getOneCustomerInfo(cartDto.getCustomerId()), productSelectService.getOnlyOneProduct(cartDto.getProductId()), cartDto);
    }
}
