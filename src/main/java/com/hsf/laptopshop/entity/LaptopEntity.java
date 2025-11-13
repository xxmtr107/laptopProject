package com.hsf.laptopshop.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "laptops")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
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

    @Column(name = "featured")
    Boolean featured; //  Sản phẩm nổi bật (true/false)

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    BrandEntity brand;

    @ManyToOne
    @JoinColumn(name = "series_id", nullable = false)
    LaptopSeriesEntity series;


    // ManyToMany với PromotionEntity
    @ManyToMany(mappedBy = "laptops")
    List<PromotionEntity> promotions;

    // --- CÁC TRƯỜNG MỚI CHO THÔNG SỐ KỸ THUẬT ---
    @Column(name = "cpu", length = 255)
    String cpu;

    @Column(name = "ram", length = 255)
    String ram;

    @Column(name = "storage", length = 255) // Ổ cứng
    String storage;

    @Column(name = "graphics_card", length = 255) // Card đồ họa
    String graphicsCard;

    @Column(name = "screen_size", length = 100) // Kích thước màn hình
    String screenSize;

    @Column(name = "screen_resolution", length = 100) // Độ phân giải
    String screenResolution;

    @Column(name = "os", length = 100) // Hệ điều hành
    String os;

    @Column(name = "battery", length = 100) // Pin
    String battery;

    @Column(name = "ports", columnDefinition = "NVARCHAR(1000)") // Cổng giao tiếp
    String ports;

}