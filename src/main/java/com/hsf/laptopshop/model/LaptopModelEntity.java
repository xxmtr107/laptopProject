package com.hsf.laptopshop.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "laptop_models")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LaptopModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    Integer modelId;
    @Column(name = "model_name")
    String modelName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "series_id")
    LaptopModelEntity laptopSeries;
}
