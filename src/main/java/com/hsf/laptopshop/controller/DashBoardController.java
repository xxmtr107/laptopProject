package com.hsf.laptopshop.controller;

import com.hsf.laptopshop.dto.DashboardDTO;
import com.hsf.laptopshop.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/admin")
public class DashBoardController {
    @Autowired
    DashboardService dashboardService;

    @GetMapping("/dashboard")
    public String getDashboardData(
            // 3. Nhận tham số ngày tháng (có thể để mặc định)
            @RequestParam(value = "startDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,

            @RequestParam(value = "endDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,

            Model model) { // <-- 4. Thêm Model

        // 5. Xử lý ngày tháng mặc định (ví dụ: 30 ngày qua)
        if (endDate == null) {
            endDate = LocalDate.now();
        }
        if (startDate == null) {
            startDate = endDate.minusDays(30);
        }

        // Chuyển đổi sang LocalDateTime để query
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);

        // 6. Vẫn gọi service y hệt như cũ
        DashboardDTO dto = dashboardService.getDashboardData(startDateTime, endDateTime);

        // 7. "Bóc tách" DTO và nhét vào Model cho Thymeleaf
        model.addAttribute("totalProducts", dto.getTotalProducts());
        model.addAttribute("totalUsers", dto.getTotalUsers());
        model.addAttribute("totalOrders", dto.getTotalOrders());

        model.addAttribute("revenueChartData", dto.getRevenueChartData()); // Gửi List cho biểu đồ

        model.addAttribute("topLaptopsList", dto.getTopLaptops()); // Gửi List cho bảng
        model.addAttribute("recentOrdersList", dto.getRecentOrders()); // Gửi List cho bảng

        // 8. Lưu lại ngày đã lọc để hiển thị trên View
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        // 9. Trả về tên của file template
        // (Ví dụ: file "dashboard.html" nằm trong /templates/admin/)
        return "/admin/dashboard";
    }
}
