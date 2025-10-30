package com.hsf.laptopshop.repository;

import com.hsf.laptopshop.entity.OrderItemEntity;
import com.hsf.laptopshop.entity.OrderItemPK;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, OrderItemPK> {
    // Top Sản Phẩm
    @Query("SELECT oi.laptop.name as laptopName, oi.laptop.image as image, SUM(oi.quantity) as totalSold " +
            "FROM OrderItemEntity oi " +
            "WHERE oi.order.createdAt BETWEEN :startDate AND :endDate " +
            "GROUP BY oi.laptop.laptopId, oi.laptop.name, oi.laptop.image " +
            "ORDER BY totalSold DESC")
    List<TopLaptopProjection> findTopSellingLaptops(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    // Top Hãng
    @Query("SELECT oi.laptop.brand.brandName as brandName, SUM(oi.quantity) as totalSold " +
            "FROM OrderItemEntity oi " +
            "WHERE oi.order.createdAt BETWEEN :startDate AND :endDate " +
            "GROUP BY oi.laptop.brand.brandName " +
            "ORDER BY totalSold DESC")
    List<TopBrandProjection> findTopSellingBrands(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
