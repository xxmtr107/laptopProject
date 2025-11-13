package com.hsf.laptopshop.security;

import com.hsf.laptopshop.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private UserEntity user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convert Role enum to GrantedAuthority
        // Spring Security expects roles to have "ROLE_" prefix
        return Collections.singleton(
            new SimpleGrantedAuthority("ROLE_" + user.getRole().name())
        );
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Helper method to get the userId for session
    public Long getUserId() {
        return user.getUserId();
    }

    // Helper method to get the full UserEntity
    public UserEntity getUserEntity() {
        return user;
    }
}
