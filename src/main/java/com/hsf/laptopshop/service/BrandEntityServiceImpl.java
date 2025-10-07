package com.hsf.laptopshop.service;

import com.hsf.laptopshop.model.BrandEntity;
import com.hsf.laptopshop.repository.BrandEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandEntityServiceImpl implements BrandEntityService {
    @Autowired
    BrandEntityRepository brandEntityRepository;

    @Override
    public boolean createBrand(BrandEntity brand) {
        return brandEntityRepository.save(brand) != null;
    }

    @Override
    public Long count() {
        return brandEntityRepository.count();
    }
}
