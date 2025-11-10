package com.hsf.laptopshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemPK implements Serializable {
    @Column(name = "order_entity_order_id")
    private Long orderId;

    // Kiểu Integer vì LaptopEntity.laptopId của bạn là Integer
    @Column(name = "laptops_laptop_id")
    private Integer laptopId;

    // Cần tự tạo equals() và hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemPK that = (OrderItemPK) o;
        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(laptopId, that.laptopId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, laptopId);
    }
}
