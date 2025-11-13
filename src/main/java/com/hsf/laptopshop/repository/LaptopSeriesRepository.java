package com.hsf.laptopshop.repository;

import com.hsf.laptopshop.entity.LaptopSeriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LaptopSeriesRepository extends JpaRepository<LaptopSeriesEntity, Integer> {
    LaptopSeriesEntity save(LaptopSeriesEntity lse);
    
    List<LaptopSeriesEntity> findByBrand_BrandId(Integer brandId);
}
