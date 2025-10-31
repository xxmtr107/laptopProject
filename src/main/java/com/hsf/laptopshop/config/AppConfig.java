package com.hsf.laptopshop.config;

import com.hsf.laptopshop.dto.OrderSimpleDTO;
import com.hsf.laptopshop.entity.OrderEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CompletableFuture;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        // CẤU HÌNH MAP NÂNG CAO (ĐÃ SỬA LỖI NULL)
        modelMapper.typeMap(OrderEntity.class, OrderSimpleDTO.class).addMappings(mapper -> {

            // 1. Chỉ map 'customerName' NẾU 'userProfile' không bị null
            mapper.map(
                    src -> (src.getUserProfile() != null) ? src.getUserProfile().getFullName() : null,
                    OrderSimpleDTO::setCustomerName
            );

            // 2. Chỉ map 'totalAmount' NẾU 'invoice' không bị null
            mapper.map(
                    src -> (src.getInvoice() != null) ? src.getInvoice().getTotalAmount() : null,
                    OrderSimpleDTO::setTotalAmount
            );

            // (Các trường tên giống nhau như orderId và createdAt sẽ tự động được map)
        });

        return modelMapper;
    }
}
