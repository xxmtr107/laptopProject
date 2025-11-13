package com.hsf.laptopshop.controller;

import com.hsf.laptopshop.entity.OrderEntity;
import com.hsf.laptopshop.entity.UserProfileEntity;
import com.hsf.laptopshop.repository.UserProfileRepository;
import com.hsf.laptopshop.security.CustomUserDetails;
import com.hsf.laptopshop.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cart")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserProfileRepository userProfileRepository;

    @GetMapping("/view")
    public String viewCart(Model model) {
        // Get the authenticated user from Spring Security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated() || 
            authentication.getPrincipal().equals("anonymousUser")) {
            return "redirect:/login";
        }
        
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUserId();
        
        UserProfileEntity user = userProfileRepository.findById(userId).get();
        OrderEntity order = orderService.getOrCreateCart(user);
        model.addAttribute("total", orderService.calculateTotal(order));
        model.addAttribute("order", order);
        return "cart";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable("id") int laptopId,
                            RedirectAttributes redirectAttributes) {
        // Get the authenticated user from Spring Security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated() || 
            authentication.getPrincipal().equals("anonymousUser")) {
            return "redirect:/login";
        }
        
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUserId();
        
        UserProfileEntity user = userProfileRepository.findById(userId).get();
        orderService.addtoCart(user, laptopId);
        redirectAttributes.addFlashAttribute("successMessage", "Item added to cart successfully!");
        return "redirect:/user/product/" + laptopId;
    }

    @PostMapping("/remove/{orderId}/{laptopId}")
    public String removeFromCart(@PathVariable("orderId") Long orderId,
                                 @PathVariable("laptopId") int laptopId,
                                 RedirectAttributes redirectAttributes) {
        orderService.cancelOrder(orderId, laptopId);
        redirectAttributes.addFlashAttribute("message", "Item removed from cart successfully.");
        return "redirect:/cart/view";
    }

//    @GetMapping("checkout/{orderId}")
//    public String checkout(@PathVariable("orderId") Long orderId,
//                           Model model) {
//        OrderEntity order = orderService.getOrderById(orderId);
//        model.addAttribute("order", order);
//        model.addAttribute("total", orderService.calculateTotal(order));
//        return "checkout";
//    }
}
