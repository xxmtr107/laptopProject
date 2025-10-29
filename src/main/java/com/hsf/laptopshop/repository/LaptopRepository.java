package com.hsf.laptopshop.repository;

import com.hsf.laptopshop.entity.LaptopEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends JpaRepository<LaptopEntity, Integer> {

    Page<LaptopEntity> findAll(Specification<LaptopEntity> spec, Pageable pageable);
}
