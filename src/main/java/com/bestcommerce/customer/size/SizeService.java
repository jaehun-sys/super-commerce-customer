package com.bestcommerce.customer.size;

import com.bestcommerce.customer.util.exception.QuantityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SizeService {
    private final SizeRepository sizeRepository;

    public Size getOneSizeInfo(Long id){
        Optional<Size> size = sizeRepository.findById(id);
        return size.orElseGet(Size::new);
    }

    @Transactional
    public void putEntityToEntityMap(Map<Long, Size> sizeMap, Long sizeId, int productCount) throws QuantityException {
        if(sizeMap.containsKey(sizeId)){
            return;
        }
        Size size = sizeRepository.findWithIdForUpdate(sizeId).orElseThrow(NullPointerException::new);
        size.orderSize(productCount);
        sizeRepository.save(size);
        sizeMap.put(size.getSizeId(), size);
    }
}
