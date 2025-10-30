package com.hsf.laptopshop.service;

import com.hsf.laptopshop.dto.DashboardDTO;
import com.hsf.laptopshop.dto.OrderSimpleDTO;
import com.hsf.laptopshop.repository.RevenueByMonthProjection;
import com.hsf.laptopshop.repository.TopBrandProjection;
import com.hsf.laptopshop.repository.TopLaptopProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    StatsService statsService;
    @Override
    public DashboardDTO getDashboardData(LocalDateTime startDate, LocalDateTime endDate) {

        //Goi tat ca cac ham async
        CompletableFuture<Long> totalLaptopsFuture = statsService.countTotalLaptops();
        CompletableFuture<Long> totalUsersFuture = statsService.countTotalUsers();
        CompletableFuture<Long> totalOrdersFuture = statsService.countTotalOrders();
        CompletableFuture<List<RevenueByMonthProjection>> revenueCharts = statsService.getRevenueByMonth(startDate, endDate);
        CompletableFuture<List<TopLaptopProjection>> topLaptops = statsService.getTopLaptops(startDate, endDate);
        CompletableFuture<List<TopBrandProjection>> topBrands = statsService.getTopBrands(startDate, endDate);
        CompletableFuture<List<OrderSimpleDTO>> recentOrders = statsService.getRecentOrders();

        CompletableFuture.allOf(totalLaptopsFuture, totalUsersFuture, totalOrdersFuture, revenueCharts, topLaptops, topBrands, recentOrders).join();

        DashboardDTO dashboardDTO = new DashboardDTO();
        try{
            dashboardDTO.setTotalProducts(totalLaptopsFuture.get());
            dashboardDTO.setTotalUsers(totalUsersFuture.get());
            dashboardDTO.setTotalOrders(totalOrdersFuture.get());
            dashboardDTO.setRevenueChartData(revenueCharts.get());
            dashboardDTO.setTopLaptops(topLaptops.get());
            dashboardDTO.setTopBrands(topBrands.get());
            dashboardDTO.setRecentOrders(recentOrders.get());
        }catch(Exception e){
            throw new RuntimeException("Error fetching dashboard data", e);
        }



        return dashboardDTO;
    }
}
