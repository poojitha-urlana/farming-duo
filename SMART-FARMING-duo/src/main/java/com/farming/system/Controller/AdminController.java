package com.farming.system.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farming.system.DTO.AdminDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

 

	    @PostMapping("/login")
	    public ResponseEntity<String> login(@RequestBody AdminDTO AdminDTO) {
	        // Add your login logic here (e.g., checking credentials, generating tokens, etc.)
	        return ResponseEntity.ok("Admin login successful!");
	    }
	}


