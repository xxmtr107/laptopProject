package com.hsf.laptopshop.repository;

import com.hsf.laptopshop.entity.OrderEntity;
import com.hsf.laptopshop.entity.UserProfileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    Optional<OrderEntity> findByUserProfileAndStatus(UserProfileEntity userProfile, String status);
    
    // Admin queries - using countQuery to avoid pagination issues with JOIN FETCH
    @Query(value = "SELECT o FROM OrderEntity o " +
            "WHERE o.status <> 'CART' " +
            "ORDER BY o.createdAt DESC",
            countQuery = "SELECT COUNT(o) FROM OrderEntity o WHERE o.status <> 'CART'")
    Page<OrderEntity> findAllOrdersExcludingCart(Pageable pageable);
    
    @Query(value = "SELECT o FROM OrderEntity o " +
            "WHERE o.status = :status " +
            "ORDER BY o.createdAt DESC",
            countQuery = "SELECT COUNT(o) FROM OrderEntity o WHERE o.status = :status")
    Page<OrderEntity> findByStatusOrderByCreatedAtDesc(@Param("status") String status, Pageable pageable);
    
    @Query(value = "SELECT o FROM OrderEntity o " +
            "LEFT JOIN o.userProfile u " +
            "WHERE (:status IS NULL OR :status = '' OR o.status = :status) " +
            "AND (:searchUser IS NULL OR :searchUser = '' OR " +
            "LOWER(u.fullName) LIKE LOWER(CONCAT('%', :searchUser, '%')) OR " +
            "LOWER(u.email) LIKE LOWER(CONCAT('%', :searchUser, '%'))) " +
            "AND o.status <> 'CART' " +
            "ORDER BY o.createdAt DESC",
            countQuery = "SELECT COUNT(o) FROM OrderEntity o " +
            "LEFT JOIN o.userProfile u " +
            "WHERE (:status IS NULL OR :status = '' OR o.status = :status) " +
            "AND (:searchUser IS NULL OR :searchUser = '' OR " +
            "LOWER(u.fullName) LIKE LOWER(CONCAT('%', :searchUser, '%')) OR " +
            "LOWER(u.email) LIKE LOWER(CONCAT('%', :searchUser, '%'))) " +
            "AND o.status <> 'CART'")
    Page<OrderEntity> searchOrders(@Param("status") String status, 
                                     @Param("searchUser") String searchUser, 
                                     Pageable pageable);
    
    long countByStatus(String status);
    
    @Query("SELECT COUNT(o) FROM OrderEntity o WHERE o.status <> 'CART'")
    long countAllOrdersExcludingCart();

}
