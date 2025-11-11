package com.hsf.laptopshop.controller;

import com.hsf.laptopshop.entity.LaptopEntity;
import com.hsf.laptopshop.service.BrandService;
import com.hsf.laptopshop.service.LaptopSeriesService;
import com.hsf.laptopshop.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/laptops")
public class AdminLaptopController {

    @Autowired
    private LaptopService laptopService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private LaptopSeriesService laptopSeriesService;

    // Hiển thị danh sách laptop với phân trang
    @GetMapping
    public String listLaptops(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<LaptopEntity> laptopPage = laptopService.findAll(pageable);
        
        model.addAttribute("laptopPage", laptopPage);
        model.addAttribute("currentPage", page);
        return "admin/listLaptop";
    }

    // Hiển thị form thêm laptop
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("laptop", new LaptopEntity());
        model.addAttribute("brands", brandService.getAllBrands());
        model.addAttribute("seriesList", laptopSeriesService.getAllSeries());
        return "admin/addLaptop";
    }

    // Xử lý thêm laptop
    @PostMapping("/add")
    public String addLaptop(@ModelAttribute LaptopEntity laptop, 
                           @RequestParam int brandId, 
                           @RequestParam int seriesId) {
        laptop.setBrand(brandService.getBrandById(brandId));
        laptop.setSeries(laptopSeriesService.getSeriesById(seriesId));
        laptopService.createLaptop(laptop);
        return "redirect:/admin/laptops";
    }

    // Hiển thị form sửa laptop
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        return laptopService.findById(id).map(laptop -> {
            model.addAttribute("laptop", laptop);
            model.addAttribute("brands", brandService.getAllBrands());
            model.addAttribute("seriesList", laptopSeriesService.getAllSeries());
            return "admin/editLaptop";
        }).orElse("redirect:/admin/laptops");
    }

    // Xử lý sửa laptop
    @PostMapping("/edit/{id}")
    public String updateLaptop(@PathVariable Integer id, 
                              @ModelAttribute LaptopEntity laptop,
                              @RequestParam int brandId, 
                              @RequestParam int seriesId) {
        laptop.setLaptopId(id);
        laptop.setBrand(brandService.getBrandById(brandId));
        laptop.setSeries(laptopSeriesService.getSeriesById(seriesId));
        laptopService.updateLaptop(laptop);
        return "redirect:/admin/laptops";
    }

    // Xóa laptop
    @GetMapping("/delete/{id}")
    public String deleteLaptop(@PathVariable Integer id) {
        laptopService.deleteLaptop(id);
        return "redirect:/admin/laptops";
    }
}
