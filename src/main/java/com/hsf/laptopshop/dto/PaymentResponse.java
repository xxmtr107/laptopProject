package com.hsf.laptopshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
    private Long orderId;
    private Long invoiceId;
    private BigDecimal amount;
    private String method;
    private String message;
    private String paymentUrl;
    private String paymentType;
    private LocalDateTime paymentDate;
}
