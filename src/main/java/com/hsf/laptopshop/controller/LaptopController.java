package com.hsf.laptopshop.controller;

import com.hsf.laptopshop.entity.BrandEntity;
import com.hsf.laptopshop.entity.LaptopEntity;
import com.hsf.laptopshop.entity.LaptopSeriesEntity;
import com.hsf.laptopshop.service.LaptopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor // Tự động tiêm LaptopService
public class LaptopController {

    private final LaptopService laptopService;

    /**
     * Xử lý trang Catalogue (trang chủ)
     * URL: /
     * Chấp nhận các tham số filter và phân trang
     */
    @GetMapping("/")
    public String showCataloguePage(
            Model model,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "brandId", required = false) Integer brandId,
            @RequestParam(name = "seriesId", required = false) Integer seriesId,
            @RequestParam(name = "minPrice", required = false) BigDecimal minPrice,
            @RequestParam(name = "maxPrice", required = false) BigDecimal maxPrice
    ) {
        // 1. Cấu hình phân trang (ví dụ: 9 sản phẩm 1 trang)
        Pageable pageable = PageRequest.of(page, 9);

        // 2. Gọi service để lọc sản phẩm
        Page<LaptopEntity> laptopPage = laptopService.filterLaptops(
                brandId, seriesId, minPrice, maxPrice, pageable
        );

        // 3. Lấy danh sách hãng và dòng máy cho bộ lọc
        List<BrandEntity> brands = laptopService.findAllBrands();
        List<LaptopSeriesEntity> series = laptopService.findAllSeries();

        // 4. Đưa tất cả dữ liệu vào Model để Thymeleaf sử dụng
        model.addAttribute("laptopPage", laptopPage); // Dữ liệu sản phẩm đã phân trang
        model.addAttribute("brands", brands);         // Dữ liệu cho dropdown hãng
        model.addAttribute("series", series);       // Dữ liệu cho dropdown dòng máy

        // 5. Gửi lại các giá trị filter đã chọn để giữ nguyên trên form
        model.addAttribute("selectedBrand", brandId);
        model.addAttribute("selectedSeries", seriesId);
        // (minPrice, maxPrice tự động được giữ lại qua param.*)

        // 6. Trả về tên file template: "catalogue.html"
        return "catalogue";
    }

    /**
     * Xử lý trang chi tiết sản phẩm
     * URL: /product/{id}
     */
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
        return "product-detail";
    }
}