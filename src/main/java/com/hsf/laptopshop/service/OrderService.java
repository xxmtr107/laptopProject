package com.hsf.laptopshop.service;

import com.hsf.laptopshop.entity.OrderEntity;
import com.hsf.laptopshop.entity.UserProfileEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    public OrderEntity getOrCreateCart(UserProfileEntity userProfile);
    public void addtoCart(UserProfileEntity userProfile, int laptopId);
    public void cancelOrder(Long orderId, Integer laptopId);
}
