package com.hsf.laptopshop.controller;

import com.hsf.laptopshop.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/promotions")
public class AdminPromotionController {

    @Autowired
    private PromotionService promotionService;

    // Hiển thị danh sách khuyến mãi
    @GetMapping
    public String listPromotions(Model model) {
        model.addAttribute("promotions", promotionService.getAllPromotions());
        return "admin/listPromotion"; // trỏ tới file:
    }
}
