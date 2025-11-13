package com.hsf.laptopshop.repository;

import com.hsf.laptopshop.entity.LaptopEntity;
import com.hsf.laptopshop.entity.OrderEntity;
import com.hsf.laptopshop.entity.OrderLaptop;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderLaptopRepository extends JpaRepository<OrderLaptop, Long> {
    Optional<OrderLaptop> findByOrderAndLaptop(OrderEntity order, LaptopEntity laptop);

    @Query("SELECT ol.laptop.name as laptopName, ol.laptop.image as image, SUM(ol.quantity) as totalSold " +
            "FROM OrderLaptop ol " +
            "WHERE ol.order.createdAt BETWEEN :startDate AND :endDate " +
            "GROUP BY ol.laptop.laptopId, ol.laptop.name, ol.laptop.image " +
            "ORDER BY totalSold DESC")
    List<TopLaptopProjection> findTopSellingLaptops(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    // 2. Top Hãng (Đổi OrderItemEntity -> OrderLaptop)
    @Query("SELECT ol.laptop.brand.brandName as brandName, SUM(ol.quantity) as totalSold " +
            "FROM OrderLaptop ol " + // SỬA Ở ĐÂY
            "WHERE ol.order.createdAt BETWEEN :startDate AND :endDate " +
            "GROUP BY ol.laptop.brand.brandName " +
            "ORDER BY totalSold DESC")
    List<TopBrandProjection> findTopSellingBrands(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
