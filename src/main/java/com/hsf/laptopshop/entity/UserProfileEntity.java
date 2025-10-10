package com.hsf.laptopshop.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "user_profiles")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_profile_id")
    Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    UserEntity user;
    @Column(name = "full_name")
    String fullName;
    @Column(name = "phone", nullable = false, length = 10)
    String phone;
}
