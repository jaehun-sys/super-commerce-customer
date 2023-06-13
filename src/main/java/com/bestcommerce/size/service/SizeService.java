package com.bestcommerce.size.service;

import com.bestcommerce.size.entity.Size;
import com.bestcommerce.size.repository.SizeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SizeService {
    private final SizeRepository sizeRepository;

    public Size getOneSizeInfo(Long id){
        Optional<Size> size = sizeRepository.findById(id);
        return size.orElseThrow(NullPointerException::new);
    }

}
