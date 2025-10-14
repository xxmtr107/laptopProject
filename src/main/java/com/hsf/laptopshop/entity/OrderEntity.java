package com.hsf.laptopshop.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Table(name = "orders")
@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    @Column(name = "order_id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long orderId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id")
    UserProfileEntity userProfile;
    @OneToMany(fetch = FetchType.LAZY)
    List<LaptopEntity> laptops = new ArrayList<LaptopEntity>();
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, optional = true)
    InvoiceEntity invoice;
}
