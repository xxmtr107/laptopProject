package com.hsf.laptopshop.service;

import com.hsf.laptopshop.entity.BrandEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {
    boolean createBrand(BrandEntity brand);

    Long count();

    BrandEntity getBrandEntityByBrandName(String brandName);

    List<BrandEntity> getAllBrands();

    BrandEntity getBrandById(int id);

    boolean updateBrand(BrandEntity brand);

    boolean deleteBrand(int id);
}
