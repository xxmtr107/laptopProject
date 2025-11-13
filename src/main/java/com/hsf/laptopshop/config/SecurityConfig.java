package com.hsf.laptopshop.config;

import com.hsf.laptopshop.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // Enable @PreAuthorize and @PostAuthorize
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // Public resources
                        .requestMatchers("/js/**", "/css/**", "/images/**", "/img/**").permitAll()
                        .requestMatchers("/login", "/register").permitAll()
                        
                        // Guest can browse these pages
                        .requestMatchers("/", "/about", "/contact").permitAll()
                        .requestMatchers("/catalogue", "/product/**").permitAll()
                        
                        // VNPay callback endpoint - must be public for VNPay to call
                        .requestMatchers("/payments/return").permitAll()
                        
                        // Cart and payment creation require authentication
                        .requestMatchers("/cart/**", "/payments/**").authenticated()
                        
                        // Admin routes - require ADMIN role
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        
                        // User routes - require USER role
                        .requestMatchers("/user/**").hasRole("USER")
                        
                        // All other requests require authentication
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler((request, response, authentication) -> {
                            // Custom success handler to redirect based on role
                            var authorities = authentication.getAuthorities();
                            String redirectUrl = "/";
                            
                            for (var authority : authorities) {
                                if (authority.getAuthority().equals("ROLE_ADMIN")) {
                                    redirectUrl = "/admin/dashboard";
                                    break;
                                } else if (authority.getAuthority().equals("ROLE_USER")) {
                                    redirectUrl = "/user/home";
                                    break;
                                }
                            }
                            response.sendRedirect(redirectUrl);
                        })
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable()); // Consider enabling CSRF in production
        return http.build();
    }
}
