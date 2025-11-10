package com.hsf.laptopshop.service;

import com.hsf.laptopshop.dto.OrderSimpleDTO;
import com.hsf.laptopshop.entity.InvoiceEntity;
import com.hsf.laptopshop.entity.OrderEntity;
import com.hsf.laptopshop.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LaptopRepository laptopRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Async
    public CompletableFuture<Long> countTotalLaptops() {
        return CompletableFuture.completedFuture(laptopRepository.count());
    }

    @Override
    @Async
    public CompletableFuture<Long> countTotalUsers() {
        return CompletableFuture.completedFuture(userRepository.count());
    }

    @Override
    @Async
    public CompletableFuture<Long> countTotalOrders() {
        return CompletableFuture.completedFuture(orderRepository.count());
    }

    @Override
    @Async
    public CompletableFuture<List<RevenueByMonthProjection>> getRevenueByMonth(LocalDateTime s, LocalDateTime e) {
        return CompletableFuture.completedFuture(invoiceRepository.findRevenueByMonth(s,e));
    }

    @Override
    @Async
    public CompletableFuture<List<TopLaptopProjection>> getTopLaptops(LocalDateTime s, LocalDateTime e) {
        Pageable top5 = PageRequest.of(0, 5);
        return CompletableFuture.completedFuture(orderItemRepository.findTopSellingLaptops(s,e,top5));
    }

    @Override
    @Async
    public CompletableFuture<List<TopBrandProjection>> getTopBrands(LocalDateTime s, LocalDateTime e) {
        Pageable top5 = PageRequest.of(0, 5);
        return CompletableFuture.completedFuture(orderItemRepository.findTopSellingBrands(s,e,top5));
    }

    @Override
    @Async
    public CompletableFuture<List<OrderSimpleDTO>> getRecentOrders() {
        Pageable top5 = PageRequest.of(0, 5);
        List<OrderEntity> recentOrders = orderRepository.findRecentOrders(top5);
        List<OrderSimpleDTO> dtoList = recentOrders.stream()
                .map(order -> modelMapper.map(order, OrderSimpleDTO.class))
                .collect(Collectors.toList());
        return CompletableFuture.completedFuture(dtoList);
    }
}
