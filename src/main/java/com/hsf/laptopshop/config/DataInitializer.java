package com.hsf.laptopshop.config;

import com.hsf.laptopshop.model.BrandEntity;
import com.hsf.laptopshop.service.BrandEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    BrandEntityService brandEntityService;

    @Override
    public void run(String... args) throws Exception {
        if (brandEntityService.count() > 0) {
            return;
        }

        // Insert the Brand Entity
        String[] name = {"Apple", "Dell", "HP", "Lenovo", "Asus", "Acer", "MSI", "Microsoft"};
        for (String brandName : name) {
            BrandEntity br = new BrandEntity();
            br.setBrandName(brandName);
            brandEntityService.createBrand(br);
        }
    }

}
