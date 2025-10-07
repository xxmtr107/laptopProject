package com.hsf.laptopshop.model;

import jakarta.persistence.*;

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

}
