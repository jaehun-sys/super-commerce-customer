package com.bestcommerce.size.service;

import com.bestcommerce.size.dto.SizeDto;
import com.bestcommerce.size.repository.SizeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class SizeService {

    private final SizeRepository sizeRepository;

    public List<SizeDto> getSizeInfoForOneProduct(Long productId){
        return sizeRepository.getSizeInfoForOneProduct(productId);
    }
}
