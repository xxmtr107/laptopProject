package com.hsf.laptopshop.repository;

import com.hsf.laptopshop.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {


    // Nếu login bằng username thì dùng:
    Optional<UserEntity> findByUsername(String username);


}
