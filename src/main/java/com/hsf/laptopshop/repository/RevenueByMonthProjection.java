package com.hsf.laptopshop.repository;

public interface RevenueByMonthProjection {
    Integer getMonth();
    Double getTotalRevenue();
}
