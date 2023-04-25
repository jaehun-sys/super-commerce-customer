package com.bestcommerce.cart.controller;

import com.bestcommerce.cart.dto.CartDto;
import com.bestcommerce.cart.dto.CartItemDto;
import com.bestcommerce.cart.dto.CartKeyDto;
import com.bestcommerce.cart.service.CartService;
import com.bestcommerce.customer.service.CustomerService;
import com.bestcommerce.product.service.ProductSelectService;
import com.bestcommerce.size.service.SizeService;
import com.bestcommerce.util.converter.EntityConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    private final ProductSelectService productSelectService;

    private final CustomerService customerService;

    private final SizeService sizeService;

    private final EntityConverter entityConverter;


    @PostMapping("/put")
    public void putProductToCart(@RequestBody CartDto cartDto){
        cartService.putProductToCart(sizeService.getOneSizeInfo(cartDto.getSizeId()), customerService.getOneCustomerInfo(cartDto.getCustomerId()), productSelectService.getOnlyOneProduct(cartDto.getProductId()), cartDto.getProductCount());
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
