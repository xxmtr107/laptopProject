package com.hsf.laptopshop.service;

import com.hsf.laptopshop.entity.BrandEntity;
import org.springframework.stereotype.Service;

@Service
public interface BrandService {
    boolean createBrand(BrandEntity brand);

    Long count();

    BrandEntity getBrandEntityByBrandName(String brandName);
}
