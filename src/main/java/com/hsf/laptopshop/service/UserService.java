package com.hsf.laptopshop.service;

import com.hsf.laptopshop.entity.UserEntity;
import com.hsf.laptopshop.entity.UserProfileEntity;

public interface UserService {
    // Kiểm tra đăng nhập dựa vào username và password
    boolean checkLogin(String username, String password);

    // Trả về user theo username (dùng cho lấy user/role sau login)
    UserEntity findByUsername(String username); // Trả về luôn UserEntity, không Optional

    // Trả về user theo ID
    UserEntity findById(Long userId);

    // Trả về user profile theo ID
    UserProfileEntity getUserProfile(Long userId);

    // Cập nhật thông tin hồ sơ người dùng
    void updateUserProfile(Long userId, String fullName, String phone, String address);
}
