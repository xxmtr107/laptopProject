package com.hsf.laptopshop.service;

import com.hsf.laptopshop.entity.UserProfileEntity;
import com.hsf.laptopshop.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements  UserProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public UserProfileEntity getUserProfileById(Long id) {
        return userProfileRepository.getReferenceById(id);
    }
}
