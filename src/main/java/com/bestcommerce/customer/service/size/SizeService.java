package com.bestcommerce.customer.service.size;

import com.bestcommerce.customer.domain.Size;
import com.bestcommerce.customer.repository.domain.SizeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SizeService {
    private final SizeRepository sizeRepository;

    public SizeService(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    public Size getOneSizeInfo(Long id){
        Optional<Size> size = sizeRepository.findById(id);
        return size.orElseGet(Size::new);
    }
}
