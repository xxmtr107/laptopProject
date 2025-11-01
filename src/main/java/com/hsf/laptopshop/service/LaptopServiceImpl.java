package com.hsf.laptopshop.service; // Sửa lại package nếu cần

import com.hsf.laptopshop.entity.BrandEntity;
import com.hsf.laptopshop.entity.LaptopEntity;
import com.hsf.laptopshop.entity.LaptopSeriesEntity;
import com.hsf.laptopshop.repository.BrandRepository;
import com.hsf.laptopshop.repository.LaptopRepository;
import com.hsf.laptopshop.repository.LaptopSeriesRepository;
import com.hsf.laptopshop.service.LaptopService;
import lombok.RequiredArgsConstructor; // <-- Dùng cái này
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // <-- Tự động tiêm (inject) các biến final bên dưới
public class LaptopServiceImpl implements LaptopService {

    // Khai báo là final để @RequiredArgsConstructor tự động tiêm vào
    private final LaptopRepository laptopRepository;
    private final BrandRepository brandRepository;
    private final LaptopSeriesRepository laptopSeriesRepository;

    // ========== PHẦN CHO CATALOGUE (GUEST/USER) ==========

    @Override
    public Page<LaptopEntity> filterLaptops(Integer brandId, Integer seriesId, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        // Dùng Specification để tạo query động

        // THAY ĐỔI Ở ĐÂY:
        // Cũ: Specification<LaptopEntity> spec = Specification.where(null);
        // Mới: Dùng cb.conjunction() để tạo một điều kiện "luôn đúng" (tương đương 1=1)
        Specification<LaptopEntity> spec = (root, query, cb) -> cb.conjunction();

        if (brandId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("brand").get("brandId"), brandId));
        }
        if (seriesId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("series").get("seriesId"), seriesId));
        }
        if (minPrice != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), maxPrice));
        }

        return laptopRepository.findAll(spec, pageable);
    }

    @Override
    public Optional<LaptopEntity> findById(Integer id) {
        return laptopRepository.findById(id);
    }

    @Override
    public List<BrandEntity> findAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public List<LaptopSeriesEntity> findAllSeries() {
        return laptopSeriesRepository.findAll();
    }


    // ========== PHẦN CHO CRUD (ADMIN) ==========

    @Override
    public Page<LaptopEntity> findAll(Pageable pageable) {
        return laptopRepository.findAll(pageable);
    }

    @Override
    public LaptopEntity createLaptop(LaptopEntity laptop) {
        // Hàm save của JpaRepository sẽ tự động tạo mới nếu ID là null
        return laptopRepository.save(laptop);
    }

    @Override
    public LaptopEntity updateLaptop(LaptopEntity laptop) {
        // Hàm save của JpaRepository cũng sẽ cập nhật nếu ID đã tồn tại
        // (Bạn nên kiểm tra laptop.getLaptopId() != null trước khi gọi)
        return laptopRepository.save(laptop);
    }

    @Override
    public void deleteLaptop(Integer id) {
        laptopRepository.deleteById(id);
    }

    @Override
    public List<LaptopEntity> findFeaturedLaptops() {
        return laptopRepository.findByFeaturedTrue();
<<<<<<< HEAD

=======
>>>>>>> Guest
    }

    @Override
    public long count() {
        return laptopRepository.count();
    }
}