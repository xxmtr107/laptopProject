package com.hsf.laptopshop.service;

import com.hsf.laptopshop.entity.UserEntity;
import com.hsf.laptopshop.entity.UserProfileEntity;
import com.hsf.laptopshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // Kiểm tra đăng nhập (rất basic, thực tế phải mã hóa mật khẩu)
    @Override
    public boolean checkLogin(String username, String password) {
        UserEntity user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }

    // Trả về user theo username (code thực tế dùng cho controller, không phải Optional)
    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    // Trả về user theo ID
    @Override
    public UserEntity findById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    // Trả về user profile theo ID
    @Override
    public UserProfileEntity getUserProfile(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getUserProfile();
    }

    // Cập nhật thông tin hồ sơ người dùng
    @Override
    @Transactional
    public void updateUserProfile(Long userId, String fullName, String phone, String address) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            UserProfileEntity profile = user.getUserProfile();
            if (profile == null) {
                // Nếu chưa có profile, tạo mới
                profile = UserProfileEntity.builder()
                        .user(user)
                        .fullName(fullName)
                        .phone(phone)
                        .address(address)
                        .build();
                user.setUserProfile(profile);
            } else {
                // Nếu đã có profile, cập nhật
                profile.setFullName(fullName);
                profile.setPhone(phone);
                profile.setAddress(address);
            }
            userRepository.save(user);
        }
    }
}
