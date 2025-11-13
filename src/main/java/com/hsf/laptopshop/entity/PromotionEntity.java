package com.hsf.laptopshop.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "promotions")
public class PromotionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_id")
    private Long id;

    @Column(name = "code", unique = true, nullable = false, length = 50)
    private String code; // Mã khuyến mãi

    @Column(name = "description", columnDefinition = "NVARCHAR(500)")
    private String description;

    @Column(name = "discount_amount")
    private BigDecimal discountAmount; // Số tiền giảm trực tiếp (VD: 200000)

    @Column(name = "discount_percent")
    private Integer discountPercent; // Giảm theo %

    @Column(name = "min_order")
    private BigDecimal minOrder; // Điều kiện giá trị đơn tối thiểu

    @Column(name = "max_uses")
    private Integer maxUses; // Số lượt dùng tối đa

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "active")
    private Boolean active = true;

    // ManyToMany với LaptopEntity
    @ManyToMany
    @JoinTable(
            name = "promotion_laptops",
            joinColumns = @JoinColumn(name = "promotion_id"),
            inverseJoinColumns = @JoinColumn(name = "laptop_id")
    )
    private List<LaptopEntity> laptops;

    // Constructors
    public PromotionEntity() {}

    public PromotionEntity(String code, String description, BigDecimal discountAmount, Integer discountPercent, BigDecimal minOrder,
                           Integer maxUses, LocalDateTime startDate, LocalDateTime endDate, Boolean active) {
        this.code = code;
        this.description = description;
        this.discountAmount = discountAmount;
        this.discountPercent = discountPercent;
        this.minOrder = minOrder;
        this.maxUses = maxUses;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = active;
    }

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(BigDecimal discountAmount) { this.discountAmount = discountAmount; }

    public Integer getDiscountPercent() { return discountPercent; }
    public void setDiscountPercent(Integer discountPercent) { this.discountPercent = discountPercent; }

    public BigDecimal getMinOrder() { return minOrder; }
    public void setMinOrder(BigDecimal minOrder) { this.minOrder = minOrder; }

    public Integer getMaxUses() { return maxUses; }
    public void setMaxUses(Integer maxUses) { this.maxUses = maxUses; }

    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public List<LaptopEntity> getLaptops() { return laptops; }
    public void setLaptops(List<LaptopEntity> laptops) { this.laptops = laptops; }
}
