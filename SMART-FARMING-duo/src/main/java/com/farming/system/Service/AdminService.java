package com.farming.system.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.farming.system.Dto.AdminDTO;
import com.farming.system.Model.Admin;
import com.farming.system.Repository.AdminRepository;



@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Admin registerAdmin(Admin admin) {
        admin.setRole("ADMIN");
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }
    public AdminDTO loginAdmin(String username, String password) {
        Admin admin = adminRepository.findByUsername(username);
        if (admin != null && passwordEncoder.matches(password, admin.getPassword())) {
            return new AdminDTO(admin.getUsername(), admin.getEmail(), admin.getRole());
        }
        return null;
    }
}
