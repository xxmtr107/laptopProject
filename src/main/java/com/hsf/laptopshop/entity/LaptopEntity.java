package com.hsf.laptopshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Entity
@Table(name = "laptops")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LaptopEntity {
    @Id
    @Column(name = "laptop_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer laptopId;
    @Column(name = "model_tier")
    String modelTier;
    @Column(name = "technical_model_id")
    String technicalModelId;
    @ManyToOne
    @JoinColumn(name = "laptop_series_id")
    LaptopSeriesEntity laptopSeriesEntity;
    @Column(name = "stock", columnDefinition = "INT default 0")
    Integer stock;
}
