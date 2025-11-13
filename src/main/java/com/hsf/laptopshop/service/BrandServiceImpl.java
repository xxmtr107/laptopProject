package com.hsf.laptopshop.service;

import com.hsf.laptopshop.entity.BrandEntity;
import com.hsf.laptopshop.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<BrandEntity> getAllBrands() {
        return brandEntityRepository.findAll();
    }

    @Override
    public BrandEntity getBrandById(int id) {
        return brandEntityRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateBrand(BrandEntity brand) {
        return brandEntityRepository.save(brand) != null;
    }

    @Override
    public boolean deleteBrand(int id) {
        try {
            brandEntityRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
