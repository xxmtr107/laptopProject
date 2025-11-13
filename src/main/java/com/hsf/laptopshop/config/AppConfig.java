package com.hsf.laptopshop.config;

import com.hsf.laptopshop.dto.OrderSimpleDTO;
import com.hsf.laptopshop.entity.OrderEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        // FIXED: use post converter instead of risky lambda mappings
        modelMapper.typeMap(OrderEntity.class, OrderSimpleDTO.class)
                .setPostConverter(context -> {
                    OrderEntity src = context.getSource();
                    OrderSimpleDTO dest = context.getDestination();

                    // Map nested fields safely
                    if (src.getUserProfile() != null) {
                        dest.setCustomerName(src.getUserProfile().getFullName());
                    }

                    if (src.getInvoice() != null) {
                        dest.setTotalAmount(src.getInvoice().getTotalAmount().doubleValue());
                    }

                    return dest;
                });

        return modelMapper;
    }
}
