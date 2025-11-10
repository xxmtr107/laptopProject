package com.hsf.laptopshop.controller;

import com.hsf.laptopshop.entity.UserEntity;
import com.hsf.laptopshop.enums.Role;
import com.hsf.laptopshop.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // login.html
    }


    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          HttpSession session,
                          Model model) {
        // Kiểm tra đăng nhập hợp lệ
        if (userService.checkLogin(username, password)) {
            UserEntity user = userService.findByUsername(username);

            // Lưu userId vào session để dùng cho chức năng là giỏ hàng
            session.setAttribute("userId", user.getUserId());

            // Phân quyền chuyển trang theo role
            if (user.getRole() == Role.ADMIN) {
                return "redirect:/admin/dashboard"; // Trang dành cho admin
            } else if (user.getRole() == Role.USER) {
                return "redirect:/user/home"; // Trang dành cho user
            } else {
                model.addAttribute("error", "Quyền tài khoản không xác định!");
                return "login";
            }
        } else {
            // Sai thông tin đăng nhập, trả lại trang login với thông báo lỗi
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng!");
            model.addAttribute("username", username);
            return "login";
        }
    }

    // Trang đăng ký
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }


}
