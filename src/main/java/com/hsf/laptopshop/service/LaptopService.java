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

    // ========== PHẦN CHO CATALOGUE (GUEST/USER) ==========

    /**
     * Lọc sản phẩm cho trang catalogue dựa trên các tiêu chí.
     * Đây là hàm filter chính của bạn.
     */
    Page<LaptopEntity> filterLaptops(
            Integer brandId,
            Integer seriesId,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Pageable pageable
    );

    /**
     * Lấy chi tiết 1 sản phẩm laptop bằng ID.
     */
    Optional<LaptopEntity> findById(Integer id);

    /**
     * Lấy tất cả các Hãng (Brand) để hiển thị ra bộ lọc.
     */
    List<BrandEntity> findAllBrands();

    /**
     * Lấy tất cả các Dòng máy (Series) để hiển thị ra bộ lọc.
     */
    List<LaptopSeriesEntity> findAllSeries();


    // ========== PHẦN CHO CRUD (ADMIN) ==========

    /**
     * Lấy tất cả laptop (có phân trang) - Dùng cho trang quản lý Admin.
     */
    Page<LaptopEntity> findAll(Pageable pageable);

    /**
     * Tạo mới một sản phẩm Laptop.
     */
    LaptopEntity createLaptop(LaptopEntity laptop);

    /**
     * Cập nhật một sản phẩm Laptop.
     */
    LaptopEntity updateLaptop(LaptopEntity laptop);

    /**
     * Xóa một sản phẩm Laptop theo ID.
     */
    void deleteLaptop(Integer id);
}