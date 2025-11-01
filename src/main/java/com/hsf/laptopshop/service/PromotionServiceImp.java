package com.hsf.laptopshop.service;

import com.hsf.laptopshop.entity.PromotionEntity;
import com.hsf.laptopshop.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PromotionServiceImp implements PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Override
    public List<PromotionEntity> getAllActivePromotions() {
        return promotionRepository.findAllByActiveTrue();
    }

    @Override
    public PromotionEntity getValidPromotion(String code, BigDecimal orderTotal) {
        PromotionEntity promo = promotionRepository.findByCodeAndActiveTrue(code);
        if (promo == null) return null;
        LocalDateTime now = LocalDateTime.now();
        boolean validDate = (promo.getStartDate() == null || !now.isBefore(promo.getStartDate())) &&
                (promo.getEndDate() == null || !now.isAfter(promo.getEndDate()));
        boolean validOrder = (promo.getMinOrder() == null || orderTotal.compareTo(promo.getMinOrder()) >= 0);
        return (validDate && validOrder) ? promo : null;
    }

    @Override
    public List<PromotionEntity> getPromotionsForLaptop(Integer laptopId) {
        return promotionRepository.findByLaptops_LaptopId(laptopId);
    }

    @Override
    public PromotionEntity save(PromotionEntity promo) {
        return promotionRepository.save(promo);
    }

    @Override
    public void delete(Long id) {
        promotionRepository.deleteById(id);
    }

    @Override
    public List<PromotionEntity> getAllPromotions() {
        return promotionRepository.findAll();
    }
}
