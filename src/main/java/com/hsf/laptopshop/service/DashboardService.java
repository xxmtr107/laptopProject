package com.hsf.laptopshop.service;

import com.hsf.laptopshop.dto.DashboardDTO;

import java.time.LocalDateTime;

public interface DashboardService {
    public DashboardDTO getDashboardData(LocalDateTime startDate, LocalDateTime endDate );
}
