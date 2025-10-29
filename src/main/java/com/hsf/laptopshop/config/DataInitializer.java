package com.hsf.laptopshop.config;

import com.hsf.laptopshop.entity.BrandEntity;
import com.hsf.laptopshop.entity.LaptopSeriesEntity;
import com.hsf.laptopshop.enums.Role;
import com.hsf.laptopshop.service.BrandService;
import com.hsf.laptopshop.service.LaptopSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final String[] BRANDS = {"Apple", "Dell", "HP", "Lenovo", "Asus", "Acer", "MSI" };
    private final String[] APPLE_SERIES = {"MacBook Air", "MacBook Pro" };
    private final String[] DELL_SERIES = {"XPS", "Inspiron", "Vostro", "Latitude", "Alienware" };
    private final String[] HP_SERIES = {"Pavilion", "Envy", "Spectre", "Victus", "ProBook" };
    private final String[] LENOVO_SERIES = {"ThinkPad", "ThinkBook", "IdeaPad", "Yoga", "Legion", "LOQ", "V Series" };
    private final String[] ASUS_SERIES = {"VivoBook", "ZenBook", "ROG", "TUF", "ExpertBook" };
    private final String[] ACER_SERIES = {"Aspire", "Swift", "Predator", "Nitro" };
    private final String[] MSI_SERIES = {"Raider", "Cyborg", "Katana", "Modern", "Prestige", "Venture" };
    private final Role[] ROLE = {Role.ADMIN, Role.USER};

    @Autowired
    BrandService brandService;
    @Autowired
    LaptopSeriesService laptopSeriesService;

    @Override
    public void run(String... args) throws Exception {
//        if (brandService.count() > 0) {
//            return;
//        }
//
//        // Insert the Brand Entity
//        for (String brandName : BRANDS) {
//            BrandEntity br = new BrandEntity();
//            br.setBrandName(brandName);
//            brandService.createBrand(br);
//        }
//
//        // Insert the Series Entity
//        // 0. For Apple
//        for (String series : APPLE_SERIES) {
//            LaptopSeriesEntity seriesEntity = new LaptopSeriesEntity();
//            seriesEntity.setSeriesName(series);
//            seriesEntity.setBrand(brandService.getBrandEntityByBrandName(BRANDS[0]));
//            laptopSeriesService.createSeries(seriesEntity);
//        }
//        // 1. For Dell
//        for (String series : DELL_SERIES) {
//            LaptopSeriesEntity seriesEntity = new LaptopSeriesEntity();
//            seriesEntity.setSeriesName(series);
//            seriesEntity.setBrand(brandService.getBrandEntityByBrandName(BRANDS[1]));
//            laptopSeriesService.createSeries(seriesEntity);
//        }
//        // 2. For HP
//        for (String series : HP_SERIES) {
//            LaptopSeriesEntity seriesEntity = new LaptopSeriesEntity();
//            seriesEntity.setSeriesName(series);
//            seriesEntity.setBrand(brandService.getBrandEntityByBrandName(BRANDS[2]));
//            laptopSeriesService.createSeries(seriesEntity);
//        }
//        // 3. For Lenovo
//        for (String series : LENOVO_SERIES) {
//            LaptopSeriesEntity seriesEntity = new LaptopSeriesEntity();
//            seriesEntity.setSeriesName(series);
//            seriesEntity.setBrand(brandService.getBrandEntityByBrandName(BRANDS[3]));
//            laptopSeriesService.createSeries(seriesEntity);
//        }
//        // 4. For Asus
//        for (String series : ASUS_SERIES) {
//            LaptopSeriesEntity seriesEntity = new LaptopSeriesEntity();
//            seriesEntity.setSeriesName(series);
//            seriesEntity.setBrand(brandService.getBrandEntityByBrandName(BRANDS[4]));
//            laptopSeriesService.createSeries(seriesEntity);
//        }
//        // 5. For Acer
//        for (String series : ACER_SERIES) {
//            LaptopSeriesEntity seriesEntity = new LaptopSeriesEntity();
//            seriesEntity.setSeriesName(series);
//            seriesEntity.setBrand(brandService.getBrandEntityByBrandName(BRANDS[5]));
//            laptopSeriesService.createSeries(seriesEntity);
//        }
//        // 6. For MSI
//        for (String series : MSI_SERIES) {
//            LaptopSeriesEntity seriesEntity = new LaptopSeriesEntity();
//            seriesEntity.setSeriesName(series);
//            seriesEntity.setBrand(brandService.getBrandEntityByBrandName(BRANDS[6]));
//            laptopSeriesService.createSeries(seriesEntity);
//        }
    }
}
