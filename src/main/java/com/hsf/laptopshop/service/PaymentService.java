package com.hsf.laptopshop.service;

import com.hsf.laptopshop.dto.PaymentResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public interface PaymentService {
    public PaymentResponse createPaymentUrl(Long orderId, BigDecimal totalAmount);
    public PaymentResponse handlePaymentCallback(Map<String, String> allParams);
}
