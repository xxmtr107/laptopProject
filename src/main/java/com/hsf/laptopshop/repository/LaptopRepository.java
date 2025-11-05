package com.hsf.laptopshop.repository;

import com.hsf.laptopshop.entity.LaptopEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaptopRepository extends JpaRepository<LaptopEntity, Integer> {

    Page<LaptopEntity> findAll(Specification<LaptopEntity> spec, Pageable pageable);

    // lấy laptop nổi bật
    List<LaptopEntity> findByFeaturedTrue();
}
