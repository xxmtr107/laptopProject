package com.hsf.laptopshop.repository;

import com.hsf.laptopshop.entity.LaptopSeriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopSeriesRepository extends JpaRepository<LaptopSeriesEntity, Integer> {
    LaptopSeriesEntity save(LaptopSeriesEntity lse);
}
