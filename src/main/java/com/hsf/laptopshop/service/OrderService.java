package com.hsf.laptopshop.service;

import com.hsf.laptopshop.entity.OrderEntity;
import com.hsf.laptopshop.entity.UserProfileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface OrderService {
    public OrderEntity getOrderById(Long orderId);
    public OrderEntity getOrCreateCart(UserProfileEntity userProfile);
    public void addtoCart(UserProfileEntity userProfile, int laptopId);
    public void cancelOrder(Long orderId, Integer laptopId);
    public BigDecimal calculateTotal(OrderEntity order);
    public List<OrderEntity> getUserOrders(UserProfileEntity userProfile);
    
    // Admin methods
    public Page<OrderEntity> getAllOrders(Pageable pageable);
    public Page<OrderEntity> getOrdersByStatus(String status, Pageable pageable);
    public Page<OrderEntity> searchOrders(String status, String searchUser, Pageable pageable);
    public void updateOrderStatus(Long orderId, String status);
    public long countOrdersByStatus(String status);
    public BigDecimal getTotalRevenue();
}
