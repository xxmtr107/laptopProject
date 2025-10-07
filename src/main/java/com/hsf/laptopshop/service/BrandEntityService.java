package com.hsf.laptopshop.service;

import com.hsf.laptopshop.model.BrandEntity;
import org.springframework.stereotype.Service;

@Service
public interface BrandEntityService {
    boolean createBrand(BrandEntity brand);

    Long count();
}
