package com.hsf.laptopshop.service;

import com.hsf.laptopshop.entity.UserEntity;
import com.hsf.laptopshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
