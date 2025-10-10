package com.hsf.laptopshop.repository;

import com.hsf.laptopshop.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Integer> {
    BrandEntity save(BrandEntity be);

    long count();

    BrandEntity findByBrandName(String brandName);
}
