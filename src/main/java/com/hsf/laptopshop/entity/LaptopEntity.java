package com.hsf.laptopshop.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

@Entity
@Table(name = "laptops")
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
}
