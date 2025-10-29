import com.hsf.laptopshop.entity.BrandEntity;
import com.hsf.laptopshop.entity.LaptopEntity;
import com.hsf.laptopshop.entity.LaptopSeriesEntity;
import com.hsf.laptopshop.repository.BrandRepository;
import com.hsf.laptopshop.repository.LaptopRepository;
import com.hsf.laptopshop.repository.LaptopSeriesRepository;
import com.hsf.laptopshop.service.LaptopService;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class LaptopServiceImpl implements LaptopService {

    private LaptopRepository laptopRepository;
    private BrandRepository brandRepository;
    private LaptopSeriesRepository laptopSeriesRepository;

    @Override
    public Page<LaptopEntity> filterLaptops(Integer brandId, Integer seriesId, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        // Dùng Specification để tạo query động
        Specification<LaptopEntity> spec = Specification.where(null);

        if (brandId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("brand").get("id"), brandId));
        }
        if (seriesId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("series").get("id"), seriesId));
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
    public Optional<LaptopEntity> getLaptopById(Integer id) {
        return laptopRepository.findById(id);
    }

    @Override
    public List<BrandEntity> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public List<LaptopSeriesEntity> getAllSeries() {
        return laptopSeriesRepository.findAll();
    }

    @Override
    public LaptopEntity saveLaptop(LaptopEntity laptop) {
        // Đây là code cho CRUD (trang admin)
        return laptopRepository.save(laptop);
    }

    @Override
    public void deleteLaptop(Integer id) {
        // Đây là code cho CRUD (trang admin)
        laptopRepository.deleteById(id);
    }
}