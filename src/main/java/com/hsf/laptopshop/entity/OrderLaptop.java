package com.hsf.laptopshop.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "orders_laptops")
@Data
public class OrderLaptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_entity_order_id")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "laptops_laptop_id")
    private LaptopEntity laptop;

    private int quantity;
}

