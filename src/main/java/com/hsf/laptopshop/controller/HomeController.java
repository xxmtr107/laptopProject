package com.hsf.laptopshop.controller;

import com.hsf.laptopshop.entity.LaptopEntity;
import com.hsf.laptopshop.service.BrandService;
import com.hsf.laptopshop.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private LaptopService laptopService;

    @Autowired
    BrandService brandService;

    @GetMapping("/")
    public String home(Model model) {
        // Lấy danh sách sản phẩm nổi bật từ service
        List<LaptopEntity> featuredLaptops = laptopService.findFeaturedLaptops();
        model.addAttribute("featuredLaptops", featuredLaptops);

        // Lấy thống kê nhỏ từ service
        Map<String, Long> homepageStats = new HashMap<>();
        homepageStats.put("totalLaptops", laptopService.count());
        homepageStats.put("totalBrands", brandService.count()); // brandService cần có count()

        // Đưa lên model để Thymeleaf sử dụng
        model.addAttribute("homepageStats", homepageStats);

        // Trả về template 'homeGuest.html' trong thư mục templates
        return "/guest/homeGuest";
    }

    // Trang giới thiệu
    @GetMapping("/about")
    public String about() {
        return "/guest/about";
    }

    // Trang liên hệ
    @GetMapping("/contact")
    public String contact() {
        return "/guest/contact";
    }
}