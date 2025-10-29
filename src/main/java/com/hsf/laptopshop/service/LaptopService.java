package com.hsf.laptopshop.service;

import com.hsf.laptopshop.entity.BrandEntity;
import com.hsf.laptopshop.entity.LaptopEntity;

import com.hsf.laptopshop.entity.LaptopSeriesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface LaptopService {

    // Lấy tất cả (có thể dùng để filter)
    Page<LaptopEntity> filterLaptops(Integer brandId, Integer seriesId, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);

    // Lấy chi tiết
    Optional<LaptopEntity> getLaptopById(Integer id);

    // Lấy tất cả brand và series để đổ ra bộ lọc
    List<BrandEntity> getAllBrands();
    List<LaptopSeriesEntity> getAllSeries();

    // CRUD (nếu cần trang admin)
    LaptopEntity saveLaptop(LaptopEntity laptop);
    void deleteLaptop(Integer id);
}