package com.hsf.laptopshop.entity;

import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "laptops")
@Data
public class LaptopEntity {
    @Id
    @Column(name = "laptop_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer laptopId;

    String name;
    BigDecimal price;

    @Column(length = 1000)
    String image;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    String description;
    Integer stock;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    BrandEntity brand;

    @ManyToOne
    @JoinColumn(name = "series_id", nullable = false)
    LaptopSeriesEntity series;

}