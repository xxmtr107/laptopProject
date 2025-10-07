package com.hsf.laptopshop.repository;

import com.hsf.laptopshop.model.LaptopModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopModelEntityRepository extends JpaRepository<LaptopModelEntity, Integer> {
    LaptopModelEntity save(LaptopModelEntity lme);
}
