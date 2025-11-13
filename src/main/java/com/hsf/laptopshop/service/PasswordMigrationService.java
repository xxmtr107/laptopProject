package com.hsf.laptopshop.service;

import com.hsf.laptopshop.entity.UserEntity;
import com.hsf.laptopshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to migrate plain text passwords to BCrypt encrypted passwords.
 * 
 * This will automatically run on application startup if enabled.
 * To enable, add @Component annotation to this class.
 * To disable, remove @Component annotation.
 */
@Service
@RequiredArgsConstructor
@Slf4j
// @Component // Uncomment this line to enable automatic password migration on startup
public class PasswordMigrationService implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // This method will run automatically on application startup if @Component is enabled
        // Comment out the line below if you don't want automatic migration
        // migratePasswords();
    }

    /**
     * Migrates all plain text passwords to BCrypt encrypted passwords.
     * This method is idempotent - it will only encrypt passwords that are not already encrypted.
     */
    public void migratePasswords() {
        log.info("Starting password migration...");
        
        List<UserEntity> users = userRepository.findAll();
        int migratedCount = 0;
        
        for (UserEntity user : users) {
            // Check if password is already BCrypt encrypted
            // BCrypt hashes start with $2a$, $2b$, or $2y$
            if (!isBCryptEncoded(user.getPassword())) {
                String plainPassword = user.getPassword();
                String encodedPassword = passwordEncoder.encode(plainPassword);
                user.setPassword(encodedPassword);
                userRepository.save(user);
                migratedCount++;
                log.info("Migrated password for user: {}", user.getUsername());
            } else {
                log.debug("Password already encrypted for user: {}", user.getUsername());
            }
        }
        
        log.info("Password migration completed. Migrated {} users out of {} total users.", 
                migratedCount, users.size());
    }

    /**
     * Check if a password string is BCrypt encoded
     */
    private boolean isBCryptEncoded(String password) {
        if (password == null || password.length() < 4) {
            return false;
        }
        
        // BCrypt hashes start with $2a$, $2b$, or $2y$ followed by cost parameter
        return password.startsWith("$2a$") || 
               password.startsWith("$2b$") || 
               password.startsWith("$2y$");
    }

    /**
     * Manually encrypt a single user's password
     * Useful for testing or one-off updates
     */
    public void encryptUserPassword(String username) {
        userRepository.findByUsername(username).ifPresent(user -> {
            if (!isBCryptEncoded(user.getPassword())) {
                String plainPassword = user.getPassword();
                String encodedPassword = passwordEncoder.encode(plainPassword);
                user.setPassword(encodedPassword);
                userRepository.save(user);
                log.info("Encrypted password for user: {}", username);
            } else {
                log.info("Password already encrypted for user: {}", username);
            }
        });
    }
}
