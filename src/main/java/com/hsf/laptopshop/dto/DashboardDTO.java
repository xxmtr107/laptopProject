package com.hsf.laptopshop.dto;

import com.hsf.laptopshop.repository.RevenueByMonthProjection;
import com.hsf.laptopshop.repository.TopBrandProjection;
import com.hsf.laptopshop.repository.TopLaptopProjection;
import jakarta.persistence.Access;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DashboardDTO {
    long totalProducts;
    long totalUsers;
    long totalOrders;

    List<RevenueByMonthProjection> revenueChartData;
    List<TopLaptopProjection> topLaptops;
    List<TopBrandProjection> topBrands;
    List<OrderSimpleDTO> recentOrders;
}
