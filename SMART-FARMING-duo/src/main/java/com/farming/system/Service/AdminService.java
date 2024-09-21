package com.farming.system.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.farming.system.Model.Admin;
import com.farming.system.Repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register Admin
    public Admin registerAdmin(Admin admin) {
        if (!admin.getPassword().equals(admin.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        admin.setRole("ADMIN");  // Set the admin role explicitly
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));  // Encrypt password
        return adminRepository.save(admin);
    }

    // Admin Login
    public Admin loginAdmin(String username, String password) {
        Admin admin = adminRepository.findByUsername(username);
        if (admin != null && passwordEncoder.matches(password, admin.getPassword())) {
            // Nullify password before returning the object
            admin.setPassword(null);
            return admin;
        }
        return null;
    }
}
