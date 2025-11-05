package com.hsf.laptopshop.service;

import com.hsf.laptopshop.entity.*;
import com.hsf.laptopshop.repository.*;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
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
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public OrderEntity getOrCreateCart(UserProfileEntity userProfile) {
        return orderRepository.findByUserProfile(userProfile)
                .orElseGet(() -> {
                    OrderEntity newCart = new OrderEntity();
                    newCart.setUserProfile(userProfile);
                    return orderRepository.save(newCart);
                });
    }

    @Override
    public void addtoCart(UserProfileEntity userProfile, int laptopId) {
        OrderEntity order = getOrCreateCart(userProfile);
        LaptopEntity laptop = laptopRepository.findById(laptopId).orElseThrow();

        Optional<OrderLaptop> existingOrderLaptop = order.getOrderLaptops()
                .stream()
                .filter(ol -> ol.getLaptop().getLaptopId() == laptopId)
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

//        InvoiceEntity invoice = order.getInvoice();
//        if (invoice == null) {
//            invoice = new InvoiceEntity();
//            invoice.setOrder(order);
//        }
//
//        invoice.setTotalAmount(total);
//        invoice.setStatus("Unpaid");
//        invoiceRepository.save(invoice);
        return total;
    }

}
