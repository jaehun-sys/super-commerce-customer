package com.bestcommerce.customer.controller.product;

import com.bestcommerce.customer.domain.Product;
import com.bestcommerce.customer.service.product.ProductSelectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/item")
@Controller
public class FilterController {

    private final ProductSelectService productSelectService;

    public FilterController(ProductSelectService productSelectService) {
        this.productSelectService = productSelectService;
    }

    @GetMapping("/all")
    public String showAllProduct(Model model){
        List<Product> allProductList = productSelectService.getAllProductList();
        model.addAttribute("list",allProductList);
        return "/product/main.html";
    }
}
