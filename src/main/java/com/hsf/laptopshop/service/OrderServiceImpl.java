package com.hsf.laptopshop.service;

import com.hsf.laptopshop.entity.*;
import com.hsf.laptopshop.repository.*;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderLaptopRepository orderLaptopRepository;
    @Autowired
    private LaptopRepository laptopRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;


    @Override
    public OrderEntity getOrderById(Long orderId) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        // Initialize all associations for detail view
        if (order.getUserProfile() != null) {
            order.getUserProfile().getFullName();
            order.getUserProfile().getEmail();
        }
        if (order.getInvoice() != null) {
            order.getInvoice().getTotalAmount();
            order.getInvoice().getPaymentMethod();
        }
        if (order.getOrderLaptops() != null && !order.getOrderLaptops().isEmpty()) {
            order.getOrderLaptops().forEach(ol -> {
                if (ol.getLaptop() != null) {
                    ol.getLaptop().getName();
                    ol.getLaptop().getPrice();
                    if (ol.getLaptop().getBrand() != null) {
                        ol.getLaptop().getBrand().getBrandName();
                    }
                }
            });
        }
        return order;
    }

//    @Override
//    public OrderEntity getOrCreateCart(UserProfileEntity userProfile) {
//        return orderRepository.findByUserProfile(userProfile)
//                .orElseGet(() -> {
//                    OrderEntity newCart = new OrderEntity();
//                    newCart.setUserProfile(userProfile);
//                    return orderRepository.save(newCart);
//                });
//    }

    @Override
    public OrderEntity getOrCreateCart(UserProfileEntity userProfile) {
        // Find an existing cart order for this user (not paid yet)
        Optional<OrderEntity> existingOrder = orderRepository.findByUserProfileAndStatus(userProfile, "CART");

        if (existingOrder.isPresent()) {
            return existingOrder.get();
        }

        // Otherwise, create a new one
        OrderEntity newCart = new OrderEntity();
        newCart.setUserProfile(userProfile);
        newCart.setStatus("CART");
        return orderRepository.save(newCart);
    }

    @Override
    public void addtoCart(UserProfileEntity userProfile, int laptopId) {
        OrderEntity order = getOrCreateCart(userProfile);
        LaptopEntity laptop = laptopRepository.findById(laptopId).orElseThrow();

        Optional<OrderLaptop> existingOrderLaptop = order.getOrderLaptops()
                .stream()
                .filter(ol -> ol.getLaptop().getLaptopId().equals(laptopId))
                .findFirst();
        if(existingOrderLaptop.isPresent()) {
            OrderLaptop ol = existingOrderLaptop.get();
            ol.setQuantity(ol.getQuantity() + 1);
        } else {
            OrderLaptop newOrderLaptop = new OrderLaptop();
            newOrderLaptop.setLaptop(laptop);
            newOrderLaptop.setQuantity(1);
            newOrderLaptop.setOrder(order);
            order.getOrderLaptops().add(newOrderLaptop);
        }
        orderRepository.save(order);
    }

    @Override
    public void cancelOrder(Long orderId, Integer laptopId) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        LaptopEntity laptop = laptopRepository.findById(laptopId)
                .orElseThrow(()-> new RuntimeException("Laptop not found"));
        OrderLaptop orderLaptop = orderLaptopRepository.findByOrderAndLaptop(order, laptop)
                .orElseThrow(() -> new RuntimeException("item not found in cart"));

        order.removeLaptop(orderLaptop);
        orderLaptopRepository.delete(orderLaptop);
        orderRepository.save(order);
    }

    @Override
    public BigDecimal calculateTotal(OrderEntity order) {
        BigDecimal total = order.getOrderLaptops().stream()
                .map(ol -> ol.getLaptop().getPrice()
                        .multiply(BigDecimal.valueOf(ol.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        InvoiceEntity invoice = invoiceRepository.findByOrder(order).orElse(null);
        if (invoice == null) {
            invoice = new InvoiceEntity();
            invoice.setOrder(order);
        }

        invoice.setTotalAmount(total);
        invoice.setStatus("Unpaid");
        invoiceRepository.save(invoice);
        return total;
    }

    @Override
    public List<OrderEntity> getUserOrders(UserProfileEntity userProfile) {
        return orderRepository.findAll().stream()
                .filter(order -> order.getUserProfile().equals(userProfile) 
                        && !"CART".equals(order.getStatus()))
                .sorted((o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt()))
                .toList();
    }

    // Admin methods implementation
    @Override
    public org.springframework.data.domain.Page<OrderEntity> getAllOrders(org.springframework.data.domain.Pageable pageable) {
        org.springframework.data.domain.Page<OrderEntity> page = orderRepository.findAllOrdersExcludingCart(pageable);
        // Initialize lazy associations
        page.getContent().forEach(this::initializeOrderAssociations);
        return page;
    }

    @Override
    public org.springframework.data.domain.Page<OrderEntity> getOrdersByStatus(String status, org.springframework.data.domain.Pageable pageable) {
        org.springframework.data.domain.Page<OrderEntity> page = orderRepository.findByStatusOrderByCreatedAtDesc(status, pageable);
        // Initialize lazy associations
        page.getContent().forEach(this::initializeOrderAssociations);
        return page;
    }

    @Override
    public org.springframework.data.domain.Page<OrderEntity> searchOrders(String status, String searchUser, org.springframework.data.domain.Pageable pageable) {
        org.springframework.data.domain.Page<OrderEntity> page = orderRepository.searchOrders(status, searchUser, pageable);
        // Initialize lazy associations
        page.getContent().forEach(this::initializeOrderAssociations);
        return page;
    }
    
    // Helper method to initialize lazy associations
    private void initializeOrderAssociations(OrderEntity order) {
        if (order.getUserProfile() != null) {
            order.getUserProfile().getFullName(); // Force initialization
        }
        if (order.getInvoice() != null) {
            order.getInvoice().getTotalAmount(); // Force initialization
        }
        if (order.getOrderLaptops() != null) {
            order.getOrderLaptops().size(); // Force initialization
        }
    }

    @Override
    public void updateOrderStatus(Long orderId, String status) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        orderRepository.save(order);
    }

    @Override
    public long countOrdersByStatus(String status) {
        return orderRepository.countByStatus(status);
    }

    @Override
    public BigDecimal getTotalRevenue() {
        return invoiceRepository.findAll().stream()
                .filter(invoice -> invoice.getOrder() != null && 
                        !"CART".equals(invoice.getOrder().getStatus()) &&
                        invoice.getTotalAmount() != null)
                .map(InvoiceEntity::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
