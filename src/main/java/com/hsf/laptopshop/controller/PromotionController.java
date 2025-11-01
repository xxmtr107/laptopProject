package com.hsf.laptopshop.controller;

import com.hsf.laptopshop.entity.PromotionEntity;
import com.hsf.laptopshop.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

// Annotation cho RESTful API
@RestController
@RequestMapping("/api/promotions")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    // API trả về tất cả promotions (hoặc chỉ active)
    @GetMapping("/active")
    public List<PromotionEntity> getAllActivePromotions() {
        return promotionService.getAllActivePromotions();
    }

    // Lấy tất cả mã
    @GetMapping("/all")
    public List<PromotionEntity> getAllPromotions() {
        return promotionService.getAllPromotions();
    }

    // API kiểm tra mã, kiểm tra điều kiện và trả về kết quả
    @GetMapping("/check")
    public ResponseEntity<?> checkPromotion(
            @RequestParam String code,
            @RequestParam BigDecimal orderTotal
    ) {
        PromotionEntity promo = promotionService.getValidPromotion(code, orderTotal);
        if (promo == null) {
            return ResponseEntity.badRequest().body("Mã code không hợp lệ hoặc không đủ điều kiện sử dụng!");
        }
        return ResponseEntity.ok(promo);
    }

    // API lấy promotion theo laptopId
    @GetMapping("/laptop/{laptopId}")
    public List<PromotionEntity> getPromotionsForLaptop(@PathVariable Integer laptopId) {
        return promotionService.getPromotionsForLaptop(laptopId);
    }
}
