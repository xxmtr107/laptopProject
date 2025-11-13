package com.hsf.laptopshop.service;

import com.hsf.laptopshop.entity.LaptopSeriesEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LaptopSeriesService {
    void createSeries(LaptopSeriesEntity laptopSeriesEntity);

    List<LaptopSeriesEntity> getAllSeries();

    LaptopSeriesEntity getSeriesById(int id);

    boolean updateSeries(LaptopSeriesEntity series);

    boolean deleteSeries(int id);
}
