package com.hsf.laptopshop.service;

import com.hsf.laptopshop.entity.InvoiceEntity;
import com.hsf.laptopshop.entity.OrderEntity;
import com.hsf.laptopshop.repository.InvoiceRepository;
import com.hsf.laptopshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void MarkInvoiceAsPaid(Long invoiceId, Long orderId, BigDecimal amount) {
        InvoiceEntity invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        OrderEntity order = orderRepository.findById(orderId)
                        .orElseThrow(() -> new RuntimeException("Order not found"));
        invoice.setOrder(order);
        invoice.setStatus("PAID");
        invoice.setTotalAmount(amount);
        invoiceRepository.save(invoice);
    }
}
