package com.bestcommerce.customer.service.size;

import com.bestcommerce.customer.domain.Size;
import com.bestcommerce.customer.repository.domain.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SizeService {
    private final SizeRepository sizeRepository;

    public Size getOneSizeInfo(Long id){
        Optional<Size> size = sizeRepository.findById(id);
        return size.orElseGet(Size::new);
    }
}
