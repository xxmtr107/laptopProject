package com.hsf.laptopshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSimpleDTO {
    Long orderId;
    String customerName;
    Double totalAmount;
    LocalDateTime createdAt;

}
