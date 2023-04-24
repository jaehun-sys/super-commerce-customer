package com.bestcommerce.customer.product;

import com.bestcommerce.customer.util.converter.DtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class FilterController {

    private final ProductSelectService productSelectService;
    private final DtoConverter dtoConverter;

    @GetMapping("/all")
    public List<ProductDto> showAllProduct(){
        return dtoConverter.toProductDtoList(productSelectService.getAllProductList());
    }
}
