package com.hsf.laptopshop.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity // <-- BẠN BỊ THIẾU DÒNG NÀY
@Table(name = "orders_laptops") // <-- THÊM DÒNG NÀY ĐỂ MAP ĐÚNG TÊN BẢNG
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemEntity {
    // 1. Dùng khóa chính phức hợp
    @EmbeddedId
    OrderItemPK id;

    // 2. Map ngược lại OrderEntity
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId") // Tên trường trong OrderItemPK
    @JoinColumn(name = "order_entity_order_id") // Tên cột FK trong bảng orders_laptops
            OrderEntity order;

    // 3. Map ngược lại LaptopEntity
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("laptopId") // Tên trường trong OrderItemPK
    @JoinColumn(name = "laptops_laptop_id") // Tên cột FK trong bảng orders_laptops
            LaptopEntity laptop;

    // 4. CỘT QUANTITY QUAN TRỌNG NHẤT
    @Column(name = "quantity", nullable = false)
    int quantity;
}
