package com.hsf.laptopshop.service;

import com.hsf.laptopshop.entity.LaptopSeriesEntity;
import com.hsf.laptopshop.repository.LaptopSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaptopSeriesServiceImpl implements LaptopSeriesService {
    @Autowired
    LaptopSeriesRepository laptopSeriesEntityRepository;

    @Override
    public void createSeries(LaptopSeriesEntity laptopSeriesEntity) {
        laptopSeriesEntityRepository.save(laptopSeriesEntity);
    }

    @Override
    public List<LaptopSeriesEntity> getAllSeries() {
        return laptopSeriesEntityRepository.findAll();
    }

    @Override
    public LaptopSeriesEntity getSeriesById(int id) {
        return laptopSeriesEntityRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateSeries(LaptopSeriesEntity series) {
        try {
            laptopSeriesEntityRepository.save(series);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteSeries(int id) {
        try {
            laptopSeriesEntityRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
