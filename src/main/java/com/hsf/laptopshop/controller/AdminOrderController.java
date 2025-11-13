package com.hsf.laptopshop.controller;

import com.hsf.laptopshop.entity.OrderEntity;
import com.hsf.laptopshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController {
    
    @Autowired
    private OrderService orderService;
    
    @GetMapping
    public String listOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String searchUser,
            Model model) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<OrderEntity> orderPage;
        
        // Search with filters
        if ((status != null && !status.isEmpty()) || (searchUser != null && !searchUser.isEmpty())) {
            orderPage = orderService.searchOrders(status, searchUser, pageable);
        } else {
            orderPage = orderService.getAllOrders(pageable);
        }
        
        // Calculate stats
        long totalOrders = orderPage.getTotalElements();
        BigDecimal totalRevenue = orderService.getTotalRevenue();
        long pendingOrders = orderService.countOrdersByStatus("pending");
        long completedOrders = orderService.countOrdersByStatus("completed");
        
        model.addAttribute("orderPage", orderPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalOrders", totalOrders);
        model.addAttribute("totalRevenue", totalRevenue);
        model.addAttribute("pendingOrders", pendingOrders);
        model.addAttribute("completedOrders", completedOrders);
        model.addAttribute("status", status);
        model.addAttribute("searchUser", searchUser);
        
        return "admin/listOrder";
    }
    
    @GetMapping("/{orderId}")
    public String viewOrderDetail(@PathVariable Long orderId, Model model) {
        OrderEntity order = orderService.getOrderById(orderId);
        BigDecimal total = orderService.calculateTotal(order);
        
        model.addAttribute("order", order);
        model.addAttribute("total", total);
        
        return "admin/orderDetail";
    }
    
    @PostMapping("/{orderId}/status")
    public String updateOrderStatus(
            @PathVariable Long orderId,
            @RequestParam String status,
            RedirectAttributes redirectAttributes) {
        
        try {
            orderService.updateOrderStatus(orderId, status);
            redirectAttributes.addFlashAttribute("successMessage", 
                    "Đã cập nhật trạng thái đơn hàng #" + orderId + " thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", 
                    "Có lỗi xảy ra khi cập nhật trạng thái: " + e.getMessage());
        }
        
        return "redirect:/admin/orders";
    }
}
