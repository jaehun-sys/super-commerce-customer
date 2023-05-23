package com.bestcommerce.product.controller;

import com.bestcommerce.customer.entity.Customer;
import com.bestcommerce.customer.service.CustomerService;
import com.bestcommerce.product.dto.BrandDetailDto;
import com.bestcommerce.product.dto.BrandDto;
import com.bestcommerce.product.dto.ProductActDto;
import com.bestcommerce.product.dto.ProductDto;
import com.bestcommerce.product.entity.Brand;
import com.bestcommerce.product.service.BrandLikeService;
import com.bestcommerce.product.service.BrandService;
import com.bestcommerce.product.service.ProductSelectService;
import com.bestcommerce.util.converter.DtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brand")
public class BrandController {

    private final CustomerService customerService;

    private final BrandService brandService;

    private final BrandLikeService brandLikeService;

    private final ProductSelectService productSelectService;

    private final DtoConverter dtoConverter;

    @PostMapping("/detail")
    public BrandDetailDto gogo(@RequestBody ProductActDto productActDto){
        Customer customer = customerService.getOneCustomerInfo(productActDto.getCustomerId());
        Brand brand = brandService.getBrand(productActDto.getBrandId());
        String isLike = brandLikeService.isLike(customer,brand);
        List<ProductDto> productList = productSelectService.getBrandProductList(customer.getCuId(), brand.getId());
        BrandDto brandDto = dtoConverter.toBrandDto(brand, isLike);
        return new BrandDetailDto(brandDto, productList);
    }
}
