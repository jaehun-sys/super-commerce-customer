package com.bestcommerce.customer.controller.product;

import com.bestcommerce.customer.domain.Product;
import com.bestcommerce.customer.service.product.ProductSelectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/item")
public class FilterController {

    private final ProductSelectService productSelectService;

    public FilterController(ProductSelectService productSelectService) {
        this.productSelectService = productSelectService;
    }

    @GetMapping("/all")
    public List<Product> showAllProduct(){
        return productSelectService.getAllProductList();
    }
}
