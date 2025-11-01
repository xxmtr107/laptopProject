package com.hsf.laptopshop.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "user_profiles")
@Data
public class UserProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_profile_id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "phone", nullable = false, length = 10)
    private String phone;
}
