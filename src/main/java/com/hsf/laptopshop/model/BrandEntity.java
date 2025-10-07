package com.hsf.laptopshop.model;

import com.hsf.laptopshop.enums.BrandName;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "brands")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    int brandId;
    @Enumerated(EnumType.STRING)
    @Column(name = "brand_name", nullable = false, unique = true)
    BrandName brandName;
}
