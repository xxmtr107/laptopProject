package com.hsf.laptopshop.service;

import com.hsf.laptopshop.entity.InvoiceEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InvoiceService {
    public List<InvoiceEntity> getAllInvoices();
}
