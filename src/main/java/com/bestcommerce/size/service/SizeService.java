package com.bestcommerce.size.service;

import com.bestcommerce.product.entity.Product;
import com.bestcommerce.size.entity.Size;
import com.bestcommerce.size.repository.SizeRepository;
import com.bestcommerce.util.exception.QuantityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SizeService {
    private final SizeRepository sizeRepository;

    public Size getOneSizeInfo(Long id){
        Optional<Size> size = sizeRepository.findById(id);
        return size.orElseThrow(NullPointerException::new);
    }

    public List<Size> getSizeListByProductDetail(Product product){
        return sizeRepository.findAllByProduct(product);
    }

    public List<Size> getSizeListByProductId(Long productId){
        return sizeRepository.getAllSizeListByProductId(productId);
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
