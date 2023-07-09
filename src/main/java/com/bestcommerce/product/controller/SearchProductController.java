package com.bestcommerce.product.controller;

import com.bestcommerce.product.dto.ProductActDto;
import com.bestcommerce.product.dto.ProductDetailDto;
import com.bestcommerce.product.dto.ProductDto;
import com.bestcommerce.product.service.ProductSelectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class SearchProductController {

    private final ProductSelectService productSelectService;

    @PostMapping("/view")
    public ProductDetailDto viewProductDetail(@RequestBody ProductActDto productActDto){
        ProductDto searchProductDto = productSelectService.getDetailProduct(productActDto.getCustomerId(), productActDto.getProductId());
        return new ProductDetailDto(searchProductDto);
    }

    @PostMapping("/search")
    public List<ProductDto> searchItem(@RequestBody ProductActDto productActDto){
        log.info("search Value : {}", productActDto.getSearchValue());
        return productSelectService.getSearchProducts(productActDto.getCustomerId(), productActDto.getSearchValue());
    }
}
