package com.bestcommerce.product.controller;

import com.bestcommerce.customer.entity.Customer;
import com.bestcommerce.customer.service.CustomerService;
import com.bestcommerce.product.dto.ProductActDto;
import com.bestcommerce.product.dto.ProductDto;
import com.bestcommerce.product.entity.Brand;
import com.bestcommerce.product.entity.Product;
import com.bestcommerce.product.service.BrandLikeService;
import com.bestcommerce.product.service.BrandService;
import com.bestcommerce.product.service.ProductLikeService;
import com.bestcommerce.product.service.ProductSelectService;
import com.bestcommerce.util.json.StatusDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {

    private final CustomerService customerService;

    private final ProductSelectService productSelectService;

    private final BrandService brandService;

    private final ProductLikeService productLikeService;

    private final BrandLikeService brandLikeService;

    @PostMapping("/product")
    public StatusDto productLikeOrCancelLike(@RequestBody ProductActDto productActDto){

        Customer customer = customerService.getOneCustomerInfo(productActDto.getCustomerId());
        Product product = productSelectService.getOnlyOneProduct(productActDto.getProductId());

        return StatusDto.builder().status(productLikeService.productLikeAct(customer, product)).build();
    }

    @PostMapping("/product/list")
    public List<ProductDto> gogo(@RequestBody ProductActDto productActDto){
        return productSelectService.getLikeProductList(productActDto.getCustomerId());
    }

    @PostMapping("/brand")
    public StatusDto brandLikeOrCancel(@RequestBody ProductActDto productActDto){

        Customer customer = customerService.getOneCustomerInfo(productActDto.getCustomerId());
        Brand brand = brandService.getBrand(productActDto.getBrandId());

        return StatusDto.builder().status(brandLikeService.brandLikeAct(customer, brand)).build();
    }

}
