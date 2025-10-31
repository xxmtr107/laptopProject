package com.hsf.laptopshop.controller;

import com.hsf.laptopshop.entity.OrderEntity;
import com.hsf.laptopshop.entity.UserProfileEntity;
import com.hsf.laptopshop.repository.UserProfileRepository;
import com.hsf.laptopshop.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserProfileRepository userProfileRepository;

    @GetMapping
    public String viewCart(Model model,
                           HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        UserProfileEntity user = userProfileRepository.findById(userId).get();
        OrderEntity order = orderService.getOrCreateCart(user);
        model.addAttribute("order", order);
        return "cart";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable("id") int laptopId,
                            HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        UserProfileEntity user = userProfileRepository.findById(userId).get();
        orderService.addtoCart(user, laptopId);
        return "redirect:/product-detail";
    }

    @PostMapping("/remove/{orderId}/{laptopId}")
    public String removeFromCart(@PathVariable("orderId") Long orderId,
                                 @PathVariable("laptopId") int laptopId,
                                 RedirectAttributes redirectAttributes) {
        orderService.cancelOrder(orderId, laptopId);
        redirectAttributes.addFlashAttribute("message", "Item removed from cart successfully.");
        return "redirect:/cart";
    }
}
