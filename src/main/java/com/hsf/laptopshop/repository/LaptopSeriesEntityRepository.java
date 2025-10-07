package com.hsf.laptopshop.repository;

import com.hsf.laptopshop.model.LaptopSeriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopSeriesEntityRepository extends JpaRepository<LaptopSeriesEntity, Integer> {
    LaptopSeriesEntity save(LaptopSeriesEntity lse);
}
