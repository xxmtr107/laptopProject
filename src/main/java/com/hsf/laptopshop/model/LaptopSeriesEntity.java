package com.hsf.laptopshop.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "laptop_series")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LaptopSeriesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "laptop_series")
    Integer seriesId;
    @Column(name = "series_name")
    String seriesName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    LaptopModelEntity laptopModel;
}
