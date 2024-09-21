package com.farming.system.Controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.farming.system.Model.Admin;
import com.farming.system.Service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Admin registration
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody Admin admin) {
        try {
            adminService.registerAdmin(admin);
            return ResponseEntity.ok(Map.of("message", "Admin registered successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    // Admin login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin admin) {
        Admin loggedInAdmin = adminService.loginAdmin(admin.getUsername(), admin.getPassword());
        if (loggedInAdmin != null) {
            return ResponseEntity.ok(loggedInAdmin);
        } else {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid username or password"));
        }
    }
}
