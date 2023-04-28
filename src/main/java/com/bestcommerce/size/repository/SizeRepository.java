package com.bestcommerce.size.repository;

import com.bestcommerce.product.entity.Product;
import com.bestcommerce.size.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface SizeRepository extends JpaRepository<Size,Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from size s where s.sizeId = :id")
    Optional<Size> findWithIdForUpdate(@Param("id") Long id);

    List<Size> findAllByProduct(Product product);
}
