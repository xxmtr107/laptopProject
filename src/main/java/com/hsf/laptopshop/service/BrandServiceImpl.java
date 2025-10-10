package com.hsf.laptopshop.service;

import com.hsf.laptopshop.entity.BrandEntity;
import com.hsf.laptopshop.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandRepository brandEntityRepository;

    @Override
    public boolean createBrand(BrandEntity brand) {
        return brandEntityRepository.save(brand) != null;
    }

    @Override
    public Long count() {
        return brandEntityRepository.count();
    }

    @Override
    public BrandEntity getBrandEntityByBrandName(String brandName) {
        return brandEntityRepository.findByBrandName(brandName);
    }
}
