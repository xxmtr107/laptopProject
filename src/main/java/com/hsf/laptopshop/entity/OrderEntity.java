package com.hsf.laptopshop.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data

public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id")
    UserProfileEntity userProfile;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLaptop> orderLaptops = new ArrayList<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    InvoiceEntity invoice;

    public void removeLaptop(OrderLaptop orderLaptop) {
        orderLaptops.remove(orderLaptop);
        orderLaptop.setOrder(null);
    }
}
