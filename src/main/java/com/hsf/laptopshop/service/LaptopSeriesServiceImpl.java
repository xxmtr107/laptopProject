package com.hsf.laptopshop.service;

import com.hsf.laptopshop.entity.LaptopSeriesEntity;
import com.hsf.laptopshop.repository.LaptopSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaptopSeriesServiceImpl implements LaptopSeriesService {
    @Autowired
    LaptopSeriesRepository laptopSeriesEntityRepository;

    @Override
    public void createSeries(LaptopSeriesEntity laptopSeriesEntity) {
        laptopSeriesEntityRepository.save(laptopSeriesEntity);
    }
}
