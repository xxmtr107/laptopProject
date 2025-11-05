package com.hsf.laptopshop.controller;

import com.hsf.laptopshop.entity.BrandEntity;
import com.hsf.laptopshop.entity.LaptopEntity;
import com.hsf.laptopshop.entity.LaptopSeriesEntity;
import com.hsf.laptopshop.service.BrandService;
import com.hsf.laptopshop.service.UserService;
import com.hsf.laptopshop.service.LaptopService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserPageController {

    @Autowired
    private LaptopService laptopService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private UserService userService;

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

    // Hồ sơ cá nhân
    @GetMapping("/profile")
    public String userProfilePage(Model model) {
        return "user/user-profile";
    }

    // Lịch sử đơn hàng
    @GetMapping("/orders")
    public String userOrdersPage(Model model) {
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
