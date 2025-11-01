package com.hsf.laptopshop.entity;

import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
<<<<<<< HEAD
import java.time.LocalDateTime;

=======
import java.util.List;
>>>>>>> Guest

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

<<<<<<< HEAD
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
=======
    @Column(name = "featured")
    Boolean featured; //  Sản phẩm nổi bật (true/false)
>>>>>>> Guest

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    BrandEntity brand;

    @ManyToOne
    @JoinColumn(name = "series_id", nullable = false)
    LaptopSeriesEntity series;


    // ManyToMany với PromotionEntity
    @ManyToMany(mappedBy = "laptops")
    List<PromotionEntity> promotions;

}