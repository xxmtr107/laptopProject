package com.hsf.laptopshop.controller;

import com.hsf.laptopshop.entity.*;
import com.hsf.laptopshop.security.SecurityUtils;
import com.hsf.laptopshop.service.BrandService;
import com.hsf.laptopshop.service.OrderService;
import com.hsf.laptopshop.service.UserService;
import com.hsf.laptopshop.service.LaptopService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserPageController {

    @Autowired
    private LaptopService laptopService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    // Trang chủ user
    @GetMapping("/home")
    public String userHomePage(Model model) {
        List<LaptopEntity> featuredLaptops = laptopService.findFeaturedLaptops();
        model.addAttribute("featuredLaptops", featuredLaptops);

        Map<String, Long> homepageStats = new HashMap<>();
        homepageStats.put("totalLaptops", laptopService.count());
        homepageStats.put("totalBrands", brandService.count());
        model.addAttribute("homepageStats", homepageStats);

        return "user/userHome";
    }

    // Trang Sản phẩm (Catalogue)
    @GetMapping("/products")
    public String showProductsPage(
            Model model,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "brandId", required = false) Integer brandId,
            @RequestParam(name = "seriesId", required = false) Integer seriesId,
            @RequestParam(name = "minPrice", required = false) BigDecimal minPrice,
            @RequestParam(name = "maxPrice", required = false) BigDecimal maxPrice
    ) {
        Pageable pageable = PageRequest.of(page, 9);
        Page<LaptopEntity> laptopPage = laptopService.filterLaptops(
                brandId, seriesId, minPrice, maxPrice, pageable
        );
        List<BrandEntity> brands = laptopService.findAllBrands();
        List<LaptopSeriesEntity> series = laptopService.findAllSeries();

        model.addAttribute("laptopPage", laptopPage);
        model.addAttribute("brands", brands);
        model.addAttribute("series", series);
        model.addAttribute("selectedBrand", brandId);
        model.addAttribute("selectedSeries", seriesId);
        return "user/user-catalogue";
    }

    // Hồ sơ cá nhân - Hiển thị
    @GetMapping("/profile")
    public String userProfilePage(Model model) {
        Long userId = SecurityUtils.getCurrentUserId();
        if (userId == null) {
            return "redirect:/login";
        }
        
        UserEntity user = userService.findById(userId);
        model.addAttribute("user", user);
        return "user/user-profile";
    }

    // Hồ sơ cá nhân - Cập nhật
    @PostMapping("/profile")
    public String updateProfile(
            @RequestParam("fullName") String fullName,
            @RequestParam("phone") String phone,
            @RequestParam("address") String address,
            RedirectAttributes redirectAttributes
    ) {
        Long userId = SecurityUtils.getCurrentUserId();
        if (userId == null) {
            return "redirect:/login";
        }

        try {
            userService.updateUserProfile(userId, fullName, phone, address);
            redirectAttributes.addFlashAttribute("success", "Cập nhật thông tin thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
        }

        return "redirect:/user/profile";
    }

    // Lịch sử đơn hàng
    @GetMapping("/orders")
    public String userOrdersPage(Model model) {
        Long userId = SecurityUtils.getCurrentUserId();
        if (userId == null) {
            return "redirect:/login";
        }
        
        UserProfileEntity userProfile = userService.getUserProfile(userId);
        List<OrderEntity> orders = orderService.getUserOrders(userProfile);
        
        // Calculate totals for each order
        Map<Long, BigDecimal> orderTotals = new HashMap<>();
        for (OrderEntity order : orders) {
            BigDecimal total = order.getOrderLaptops().stream()
                    .map(ol -> ol.getLaptop().getPrice().multiply(BigDecimal.valueOf(ol.getQuantity())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            orderTotals.put(order.getOrderId(), total);
        }
        
        model.addAttribute("orders", orders);
        model.addAttribute("orderTotals", orderTotals);
        
        return "user/history";
    }

    @GetMapping("/product/{id}")
    public String showProductDetailPage(@PathVariable("id") Integer id, Model model) {

        // 1. Gọi service để tìm laptop theo ID
        Optional<LaptopEntity> laptopOptional = laptopService.findById(id);

        // 2. Kiểm tra xem có tìm thấy không
        if (laptopOptional.isPresent()) {
            // Nếu tìm thấy, đưa laptop vào model
            model.addAttribute("laptop", laptopOptional.get());
        } else {
            // Nếu không tìm thấy, đưa null vào model
            // (File product-detail.html đã sửa sẽ xử lý trường hợp này)
            model.addAttribute("laptop", null);
        }

        // 3. Trả về tên file template: "product-detail.html"
        return "/user/user-product-detail";
    }
}
