package com.hsf.laptopshop.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

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

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    BrandEntity brand;

    @ManyToOne
    @JoinColumn(name = "series_id", nullable = false)
    LaptopSeriesEntity series;

}