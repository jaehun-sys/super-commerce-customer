package com.bestcommerce.customer.controller.cart;

import com.bestcommerce.customer.dto.CartDto;
import com.bestcommerce.customer.dto.CartItemDto;
import com.bestcommerce.customer.dto.CartKeyDto;
import com.bestcommerce.customer.service.customer.CustomerService;
import com.bestcommerce.customer.service.cart.CartService;
import com.bestcommerce.customer.service.product.ProductSelectService;
import com.bestcommerce.customer.service.size.SizeService;
import com.bestcommerce.customer.util.EntityConverter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    private final ProductSelectService productSelectService;

    private final CustomerService customerService;

    private final SizeService sizeService;

    private final EntityConverter entityConverter;

    public CartController(CartService cartService, ProductSelectService productSelectService, CustomerService customerService, SizeService sizeService, EntityConverter entityConverter){
        this.cartService = cartService;
        this.productSelectService = productSelectService;
        this.customerService = customerService;
        this.sizeService = sizeService;
        this.entityConverter = entityConverter;
    }

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
