package com.hsf.laptopshop.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Table(name = "brands")
@Data
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    int brandId;
    @Column(name = "brand_name", nullable = false, unique = true, length = 50)
    String brandName;

    @OneToMany(mappedBy = "brand")
    private Set<LaptopEntity> laptops;
}
