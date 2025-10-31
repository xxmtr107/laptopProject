package com.hsf.laptopshop.repository;

import com.hsf.laptopshop.entity.OrderEntity;
import com.hsf.laptopshop.entity.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
    Optional<OrderEntity> findByUserProfile(UserProfileEntity userProfile);
}
