package com.bestcommerce.customer.controller.product;

import com.bestcommerce.customer.dto.ProductDto;
import com.bestcommerce.customer.service.product.ProductSelectService;
import com.bestcommerce.customer.util.DtoConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/item")
public class FilterController {

    private final ProductSelectService productSelectService;
    private final DtoConverter dtoConverter;

    public FilterController(ProductSelectService productSelectService, DtoConverter dtoConverter) {
        this.productSelectService = productSelectService;
        this.dtoConverter = dtoConverter;
    }

    @GetMapping("/all")
    public List<ProductDto> showAllProduct(){
        return dtoConverter.toProductDtoList(productSelectService.getAllProductList());
    }
}
