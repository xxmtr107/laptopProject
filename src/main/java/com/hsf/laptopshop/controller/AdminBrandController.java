package com.hsf.laptopshop.controller;

import com.hsf.laptopshop.entity.BrandEntity;
import com.hsf.laptopshop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/brands")
public class AdminBrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public String listBrands(Model model) {
        model.addAttribute("brands", brandService.getAllBrands());
        return "admin/listBrand";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("brand", new BrandEntity());
        return "admin/addBrand";
    }

    @PostMapping("/add")
    public String addBrand(@ModelAttribute BrandEntity brand) {
        brandService.createBrand(brand);
        return "redirect:/admin/brands";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        BrandEntity brand = brandService.getBrandById(id);
        if (brand == null) {
            return "redirect:/admin/brands";
        }
        model.addAttribute("brand", brand);
        return "admin/editBrand";
    }

    @PostMapping("/edit/{id}")
    public String updateBrand(@PathVariable int id, @ModelAttribute BrandEntity brand) {
        brand.setBrandId(id);
        brandService.updateBrand(brand);
        return "redirect:/admin/brands";
    }

    @GetMapping("/delete/{id}")
    public String deleteBrand(@PathVariable int id) {
        brandService.deleteBrand(id);
        return "redirect:/admin/brands";
    }
}
