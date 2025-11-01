package com.hsf.laptopshop.service;

import com.hsf.laptopshop.entity.PromotionEntity;

import java.math.BigDecimal;
import java.util.List;

public interface PromotionService {
    // Lấy tất cả promotion đang active
    List<PromotionEntity> getAllActivePromotions();

    // Lấy promotion theo code và kiểm tra điều kiện
    PromotionEntity getValidPromotion(String code, BigDecimal orderTotal);

    // Lấy khuyến mãi đang áp dụng cho laptopId
    List<PromotionEntity> getPromotionsForLaptop(Integer laptopId);

    // Có thể bổ sung CRUD nếu cần
    PromotionEntity save(PromotionEntity promo);
    void delete(Long id);

    List<PromotionEntity> getAllPromotions();
}
