package com.hsf.laptopshop.repository;

import com.hsf.laptopshop.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {
    @Query("SELECT FUNCTION('MONTH', i.order.createdAt) as month, SUM(i.totalAmount) as revenue " +
            "FROM InvoiceEntity i " +
            "WHERE i.order.createdAt BETWEEN :startDate AND :endDate " +
            "GROUP BY FUNCTION('MONTH', i.order.createdAt) " +
            "ORDER BY FUNCTION('MONTH', i.order.createdAt) ASC")
    List<RevenueByMonthProjection> findRevenueByMonth(LocalDateTime startDate, LocalDateTime endDate);
}
