package com.hsf.laptopshop.service;

import com.hsf.laptopshop.entity.LaptopSeriesEntity;
import org.springframework.stereotype.Service;

@Service
public interface LaptopSeriesService {
    void createSeries(LaptopSeriesEntity laptopSeriesEntity);
}
