package com.hsf.laptopshop.service;

import com.hsf.laptopshop.dto.OrderSimpleDTO;
import com.hsf.laptopshop.repository.RevenueByMonthProjection;
import com.hsf.laptopshop.repository.TopBrandProjection;
import com.hsf.laptopshop.repository.TopLaptopProjection;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;


public interface StatsService {
    public CompletableFuture<Long> countTotalLaptops();
    public CompletableFuture<Long> countTotalUsers();
    public CompletableFuture<Long> countTotalOrders();
    public CompletableFuture<List<RevenueByMonthProjection>> getRevenueByMonth(LocalDateTime s, LocalDateTime e);
    public CompletableFuture<List<TopLaptopProjection>> getTopLaptops(LocalDateTime s, LocalDateTime e);
    public CompletableFuture<List<TopBrandProjection>> getTopBrands(LocalDateTime s, LocalDateTime e);
    public CompletableFuture<List<OrderSimpleDTO>> getRecentOrders();
}
