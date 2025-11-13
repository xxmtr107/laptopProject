package com.hsf.laptopshop.repository;

import com.hsf.laptopshop.entity.PromotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<PromotionEntity, Long> {
    PromotionEntity findByCodeAndActiveTrue(String code);
    List<PromotionEntity> findAllByActiveTrue();

    // Tìm promotion áp dụng cho một laptop cụ thể (productId)
    List<PromotionEntity> findByLaptops_LaptopId(Integer laptopId);
}
