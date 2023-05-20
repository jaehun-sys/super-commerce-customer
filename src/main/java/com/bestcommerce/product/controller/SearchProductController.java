package com.bestcommerce.product.controller;

import com.bestcommerce.product.dto.ProductActDto;
import com.bestcommerce.product.dto.ProductDetailDto;
import com.bestcommerce.product.dto.ProductDto;
import com.bestcommerce.product.service.ProductSelectService;
import com.bestcommerce.size.dto.SizeDto;
import com.bestcommerce.size.service.SizeService;
import com.bestcommerce.util.converter.DtoConverter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class SearchProductController {

    private static final Logger log = LoggerFactory.getLogger(SearchProductController.class);

    private final ProductSelectService productSelectService;
    private final SizeService sizeService;
    private final DtoConverter dtoConverter;

    @PostMapping("/view")
    public ProductDetailDto viewProductDetail(@RequestBody ProductActDto productActDto){
        ProductDto searchProductDto = productSelectService.getDetailProduct(productActDto.getCustomerId(), productActDto.getProductId());
        List<SizeDto> searchProductSizeList = dtoConverter.toSizeDtoList(sizeService.getSizeListByProductId(productActDto.getProductId()));
        return new ProductDetailDto(searchProductDto, searchProductSizeList);
    }

    @PostMapping("/search")
    public List<ProductDto> searchItem(@RequestBody ProductActDto productActDto){
        log.info("search Value : {}", productActDto.getSearchValue());
        return productSelectService.getSearchProducts(productActDto.getCustomerId(), productActDto.getSearchValue());
    }
}
