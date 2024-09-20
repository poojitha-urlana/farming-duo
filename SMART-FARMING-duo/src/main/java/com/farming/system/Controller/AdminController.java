package com.farming.system.Controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.farming.system.Dto.AdminDTO;
import com.farming.system.Model.Admin;
import com.farming.system.Service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody Admin admin) {
//        Admin registeredAdmin = adminService.registerAdmin(admin);
//        if (registeredAdmin != null) {
//            return ResponseEntity.ok("Admin registered successfully");
//        } else {
//            return ResponseEntity.badRequest().body("Error registering admin");
//        }
//    }
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody Admin admin) {
        Admin registeredAdmin = adminService.registerAdmin(admin);
        if (registeredAdmin != null) {
            return ResponseEntity.ok(Map.of("message", "Admin registered successfully"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("message", "Error registering admin"));
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin admin) {
        AdminDTO loggedInAdmin = adminService.loginAdmin(admin.getUsername(), admin.getPassword());
        if (loggedInAdmin != null) {
            return ResponseEntity.ok(loggedInAdmin); // Return AdminDTO on successful login
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}