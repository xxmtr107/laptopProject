package com.hsf.laptopshop.controller;

import com.hsf.laptopshop.dto.PaymentResponse;
import com.hsf.laptopshop.entity.OrderEntity;
import com.hsf.laptopshop.repository.OrderRepository;
import com.hsf.laptopshop.service.OrderService;
import com.hsf.laptopshop.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@Controller
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private OrderService orderService;

    @PostMapping("/create/{id}")
    public String createPayment(@PathVariable("id") Long orderId) {
        OrderEntity order = orderService.getOrderById(orderId);
        BigDecimal totalAmount = orderService.calculateTotal(order);
        PaymentResponse paymentResponse = paymentService.createPaymentUrl(orderId, totalAmount);
        return "redirect:" + paymentResponse.getPaymentUrl();
    }
    @GetMapping("/return")
    public String paymentReturn(@RequestParam Map<String,String> allParams,
                                Model model) {
        PaymentResponse paymentResponse = paymentService.handlePaymentCallback(allParams);
        OrderEntity order = orderService.getOrderById(paymentResponse.getOrderId());
        model.addAttribute("paymentResponse", paymentResponse);
        model.addAttribute("order", order);
        return "checkout";
    }
}
