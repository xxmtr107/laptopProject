package com.hsf.laptopshop.controller;

import com.hsf.laptopshop.entity.LaptopSeriesEntity;
import com.hsf.laptopshop.service.BrandService;
import com.hsf.laptopshop.service.LaptopSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/series")
public class AdminSeriesController {

    @Autowired
    private LaptopSeriesService laptopSeriesService;

    @Autowired
    private BrandService brandService;

    // Hiển thị danh sách series
    @GetMapping
    public String listSeries(Model model) {
        model.addAttribute("seriesList", laptopSeriesService.getAllSeries());
        return "admin/listSeries";
    }

    // Hiển thị form thêm series
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("series", new LaptopSeriesEntity());
        model.addAttribute("brands", brandService.getAllBrands());
        return "admin/addSeries";
    }

    // Xử lý thêm series
    @PostMapping("/add")
    public String addSeries(@ModelAttribute LaptopSeriesEntity series, @RequestParam int brandId) {
        series.setBrand(brandService.getBrandById(brandId));
        laptopSeriesService.createSeries(series);
        return "redirect:/admin/series";
    }

    // Hiển thị form sửa series
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        LaptopSeriesEntity series = laptopSeriesService.getSeriesById(id);
        if (series == null) {
            return "redirect:/admin/series";
        }
        model.addAttribute("series", series);
        model.addAttribute("brands", brandService.getAllBrands());
        return "admin/editSeries";
    }

    // Xử lý sửa series
    @PostMapping("/edit/{id}")
    public String updateSeries(@PathVariable int id, @ModelAttribute LaptopSeriesEntity series, @RequestParam int brandId) {
        series.setSeriesId(id);
        series.setBrand(brandService.getBrandById(brandId));
        laptopSeriesService.updateSeries(series);
        return "redirect:/admin/series";
    }

    // Xóa series
    @GetMapping("/delete/{id}")
    public String deleteSeries(@PathVariable int id) {
        laptopSeriesService.deleteSeries(id);
        return "redirect:/admin/series";
    }
}
