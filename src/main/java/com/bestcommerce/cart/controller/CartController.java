package com.bestcommerce.cart.controller;

import com.bestcommerce.cart.dto.CartDto;
import com.bestcommerce.cart.dto.CartItemDto;
import com.bestcommerce.cart.dto.CartKeyDto;
import com.bestcommerce.cart.service.CartService;
import com.bestcommerce.customer.service.CustomerService;
import com.bestcommerce.util.converter.EntityConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    private final CustomerService customerService;

    private final EntityConverter entityConverter;


    @PostMapping("/put")
    public void putProductToCart(@RequestBody CartDto cartDto){
        cartService.putProductToCart(customerService.getOneCustomerInfo(cartDto.getCustomerId()), cartDto.getQuantityId(), cartDto.getProductCount());
    }

    @PostMapping("/list")
    public List<CartItemDto> getCartItemList(@RequestBody CartItemDto cartItemDto){
        return cartService.getCartList(cartItemDto.getCustomerId());
    }

    @PostMapping("/delete")
    public void deleteCartList(@RequestBody List<CartKeyDto> cartKeyDtoList){
        cartService.deleteCartList(entityConverter.toCartKeyList(cartKeyDtoList));
    }
}
