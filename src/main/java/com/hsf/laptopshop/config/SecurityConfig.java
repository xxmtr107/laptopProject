package com.hsf.laptopshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize

                        // 1. CHO PHÉP TẤT CẢ CÁC FILE TĨNH (Lỗi của bạn ở đây)
                        .requestMatchers("/js/**", "/css/**", "/images/**").permitAll()

                        // 2. CHO PHÉP TRANG DASHBOARD (URL TỪ CONTROLLER)
                        .requestMatchers("/admin/dashboard").permitAll()

                        // 3. TẠM THỜI CHO PHÉP MỌI THỨ KHÁC
                        .anyRequest().permitAll()
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}