package com.hsf.laptopshop.repository;

import com.hsf.laptopshop.entity.LaptopEntity;
import com.hsf.laptopshop.entity.OrderEntity;
import com.hsf.laptopshop.entity.OrderLaptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderLaptopRepository extends JpaRepository<OrderLaptop, Long> {
    Optional<OrderLaptop> findByOrderAndLaptop(OrderEntity order, LaptopEntity laptop);
}
