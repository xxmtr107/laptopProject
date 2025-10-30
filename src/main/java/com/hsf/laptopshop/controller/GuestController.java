package com.hsf.laptopshop.controller;

import com.hsf.laptopshop.entity.LaptopEntity;
import com.hsf.laptopshop.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GuestController {

    @Autowired
    LaptopService laptopService;

    // API lấy danh sách laptop nổi bật
    @GetMapping("/featured-laptops")
    public List<LaptopEntity> getFeaturedLaptops() {
        return laptopService.findFeaturedLaptops();
    }
}
