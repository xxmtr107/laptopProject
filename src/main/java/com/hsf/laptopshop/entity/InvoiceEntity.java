package com.hsf.laptopshop.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Table(name = "invoices")
@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    Long invoiceId;
    @OneToOne
    @JoinColumn(name = "order_id")
    OrderEntity order;
    @Column(name = "total_amount")
    Double totalAmount;
}
