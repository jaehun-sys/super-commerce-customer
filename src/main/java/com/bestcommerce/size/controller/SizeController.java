package com.bestcommerce.size.controller;

import com.bestcommerce.product.service.ProductSelectService;
import com.bestcommerce.size.service.SizeService;
import com.bestcommerce.util.converter.DtoConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/size")
public class SizeController {

    private final SizeService sizeService;

    private final ProductSelectService productSelectService;

    private final DtoConverter dtoConverter;

//    @PostMapping("/view")
//    public List<SizeDto> getDetailProductSize(@RequestBody ProductDto productDto){
//        return dtoConverter.toSizeDtoList(sizeService.getSizeListByProductDetail(productSelectService.getOnlyOneProduct(productDto.getProductId())));
//    }
}
