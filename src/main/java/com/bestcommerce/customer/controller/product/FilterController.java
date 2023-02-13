package com.bestcommerce.customer.controller.product;

import com.bestcommerce.customer.domain.Product;
import com.bestcommerce.customer.dto.ProductDto;
import com.bestcommerce.customer.service.product.ProductSelectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/item")
public class FilterController {

    private final ProductSelectService productSelectService;

    public FilterController(ProductSelectService productSelectService) {
        this.productSelectService = productSelectService;
    }

    @GetMapping("/all")
    public List<ProductDto> showAllProduct(){
        List<Product> entityList = productSelectService.getAllProductList();
        List<ProductDto> dtoList = new ArrayList<>();
        for(Product product : entityList){
            dtoList.add(new ProductDto(product.getProductId()
                                        ,product.getProductName()
                                        ,product.getProductCost()
                                        ,product.getInfo()
                                        ,product.getThumbPath()
                                        ,product.getDeliveryCost()));
        }
        return dtoList;
    }
}
