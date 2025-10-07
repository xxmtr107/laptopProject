package com.hsf.laptopshop.service;

import com.hsf.laptopshop.model.LaptopSeriesEntity;
import org.springframework.stereotype.Service;

@Service
public interface LaptopSeriesEntityService {
    void createSeries(LaptopSeriesEntity laptopSeriesEntity);
}
