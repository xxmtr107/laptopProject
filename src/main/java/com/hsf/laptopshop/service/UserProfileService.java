package com.hsf.laptopshop.service;

import com.hsf.laptopshop.entity.UserProfileEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserProfileService {
    public UserProfileEntity getUserProfileById(Long id);
}
