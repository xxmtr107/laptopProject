package com.hsf.laptopshop.service;

import com.hsf.laptopshop.model.LaptopSeriesEntity;
import com.hsf.laptopshop.repository.LaptopSeriesEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaptopSeriesEntityServiceImpl implements LaptopSeriesEntityService {
    @Autowired
    LaptopSeriesEntityRepository laptopSeriesEntityRepository;

    @Override
    public void createSeries(LaptopSeriesEntity laptopSeriesEntity) {
        laptopSeriesEntityRepository.save(laptopSeriesEntity);
    }
}
