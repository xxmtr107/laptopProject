package com.hsf.laptopshop.repository;

import com.hsf.laptopshop.entity.OrderEntity;
import com.hsf.laptopshop.entity.UserProfileEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    // Lấy đơn hàng mới nhất (join để lấy đủ thông tin)
    @Query("SELECT o FROM OrderEntity o " +
            "LEFT JOIN FETCH o.userProfile " +
            "LEFT JOIN FETCH o.invoice " +
            "ORDER BY o.createdAt DESC")
    List<OrderEntity> findRecentOrders(Pageable pageable);

    Optional<OrderEntity> findByUserProfile(UserProfileEntity userProfile);


}
