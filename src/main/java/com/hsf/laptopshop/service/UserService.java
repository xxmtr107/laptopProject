package com.hsf.laptopshop.service;

import com.hsf.laptopshop.entity.UserEntity;

public interface UserService {
    // Kiểm tra đăng nhập dựa vào username và password
    boolean checkLogin(String username, String password);

    // Trả về user theo username (dùng cho lấy user/role sau login)
    UserEntity findByUsername(String username); // Trả về luôn UserEntity, không Optional
}
