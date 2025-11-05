package com.hsf.laptopshop.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Table(name = "invoices")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    Long invoiceId;
    @OneToOne
    @JoinColumn(name = "order_id")
    OrderEntity order;
    @Column(name = "total_amount")
    BigDecimal totalAmount;
    @Column(name = "status")
    String status;
}
