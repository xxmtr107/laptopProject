package com.hsf.laptopshop.entity;

import com.hsf.laptopshop.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "users")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Long userId;
    @Column(name = "username", unique = true, length = 50)
    String username;
    @Column(name = "password")
    String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    Role role;
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL, optional = true)
    UserProfileEntity userProfile;
}
