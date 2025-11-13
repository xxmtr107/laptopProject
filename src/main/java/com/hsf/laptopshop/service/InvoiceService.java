package com.hsf.laptopshop.service;

import com.hsf.laptopshop.entity.InvoiceEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface InvoiceService {
    public void MarkInvoiceAsPaid(Long invoiceId, Long orderId, BigDecimal amount);
}
