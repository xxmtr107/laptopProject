package com.hsf.laptopshop.config;

import com.hsf.laptopshop.model.BrandEntity;
import com.hsf.laptopshop.model.LaptopSeriesEntity;
import com.hsf.laptopshop.service.BrandEntityService;
import com.hsf.laptopshop.service.LaptopSeriesEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final String[] BRANDS = {"Apple", "Dell", "HP", "Lenovo", "Asus", "Acer", "MSI", "Microsoft" };
    private final String[] APPLE_SERIES = {"MacBook", "MacBook Air", "MacBook Pro" };
    private final String[] DELL_SERIES = {"XPS", "Inspiron", "Vostro", "Latitude", "Precision", "Alienware", "G Series", "Chromebook" };
    private final String[] HP_SERIES = {"Pavilion", "Envy", "Spectre", "Omen", "Victus", "EliteBook", "ProBook", "ZBook", "Chromebook" };
    private final String[] LENOVO_SERIES = {"ThinkPad", "ThinkBook", "IdeaPad", "Yoga", "Legion", "LOQ", "V Series", "Chromebook" };
    private final String[] ASUS_SERIES = {"VivoBook", "ZenBook", "ROG", "TUF", "ExpertBook", "ProArt StudioBook", "Chromebook", "BR Series" };
    private final String[] ACER_SERIES = {"Aspire", "Swift", "Spin", "Predator", "Nitro", "TravelMate", "Enduro", "Chromebook" };
    private final String[] MSI_SERIES = {"Stealth", "Raider", "Titan", "Vector", "Cyborg", "Katana", "Pulse", "Modern", "Prestige", "Creator" };
    private final String[] MICROSOFT_SERIES = {"Surface Laptop", "Surface Pro", "Surface Go", "Surface Book", "Surface Studio" };

    @Autowired
    BrandEntityService brandEntityService;
    @Autowired
    LaptopSeriesEntityService laptopSeriesEntityService;

    @Override
    public void run(String... args) throws Exception {
        if (brandEntityService.count() > 0) {
            return;
        }

        // Insert the Brand Entity
        for (String brandName : BRANDS) {
            BrandEntity br = new BrandEntity();
            br.setBrandName(brandName);
            brandEntityService.createBrand(br);
        }

        // Insert the Series Entity
        // 0. For Apple
        for (String series : APPLE_SERIES) {
            LaptopSeriesEntity seriesEntity = new LaptopSeriesEntity();
            seriesEntity.setSeriesName(series);
            seriesEntity.setBrand(brandEntityService.getBrandEntityByBrandName(BRANDS[0]));
            laptopSeriesEntityService.createSeries(seriesEntity);
        }
        // 1. For Dell
        for (String series : DELL_SERIES) {
            LaptopSeriesEntity seriesEntity = new LaptopSeriesEntity();
            seriesEntity.setSeriesName(series);
            seriesEntity.setBrand(brandEntityService.getBrandEntityByBrandName(BRANDS[1]));
            laptopSeriesEntityService.createSeries(seriesEntity);
        }
        // 2. For HP
        for (String series : HP_SERIES) {
            LaptopSeriesEntity seriesEntity = new LaptopSeriesEntity();
            seriesEntity.setSeriesName(series);
            seriesEntity.setBrand(brandEntityService.getBrandEntityByBrandName(BRANDS[2]));
            laptopSeriesEntityService.createSeries(seriesEntity);
        }
        // 3. For Lenovo
        for (String series : LENOVO_SERIES) {
            LaptopSeriesEntity seriesEntity = new LaptopSeriesEntity();
            seriesEntity.setSeriesName(series);
            seriesEntity.setBrand(brandEntityService.getBrandEntityByBrandName(BRANDS[3]));
            laptopSeriesEntityService.createSeries(seriesEntity);
        }
        // 4. For Asus
        for (String series : ASUS_SERIES) {
            LaptopSeriesEntity seriesEntity = new LaptopSeriesEntity();
            seriesEntity.setSeriesName(series);
            seriesEntity.setBrand(brandEntityService.getBrandEntityByBrandName(BRANDS[4]));
            laptopSeriesEntityService.createSeries(seriesEntity);
        }
        // 5. For Acer
        for (String series : ACER_SERIES) {
            LaptopSeriesEntity seriesEntity = new LaptopSeriesEntity();
            seriesEntity.setSeriesName(series);
            seriesEntity.setBrand(brandEntityService.getBrandEntityByBrandName(BRANDS[5]));
            laptopSeriesEntityService.createSeries(seriesEntity);
        }
        // 6. For MSI
        for (String series : MSI_SERIES) {
            LaptopSeriesEntity seriesEntity = new LaptopSeriesEntity();
            seriesEntity.setSeriesName(series);
            seriesEntity.setBrand(brandEntityService.getBrandEntityByBrandName(BRANDS[6]));
            laptopSeriesEntityService.createSeries(seriesEntity);
        }
        // 7. For Microsoft
        for (String series : MICROSOFT_SERIES) {
            LaptopSeriesEntity seriesEntity = new LaptopSeriesEntity();
            seriesEntity.setSeriesName(series);
            seriesEntity.setBrand(brandEntityService.getBrandEntityByBrandName(BRANDS[7]));
            laptopSeriesEntityService.createSeries(seriesEntity);
        }
    }
}
