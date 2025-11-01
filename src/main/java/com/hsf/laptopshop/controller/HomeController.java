package com.hsf.laptopshop.controller;

import com.hsf.laptopshop.entity.LaptopEntity;
import com.hsf.laptopshop.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private LaptopService laptopService;

    @GetMapping("/")
    public String home(Model model) {
        // Lấy danh sách sản phẩm nổi bật từ service
        List<LaptopEntity> featuredLaptops = laptopService.findFeaturedLaptops();

        // Đưa lên model để template Thymeleaf sử dụng
        model.addAttribute("featuredLaptops", featuredLaptops);

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
