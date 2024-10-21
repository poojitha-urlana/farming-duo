package com.farming.system.Controller;

import com.farming.system.DTO.AdminDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/admin")
public class AdminLoginController {

    private final AuthenticationManager authenticationManager;

    public AdminLoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    // Admin login
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> adminLogin(@RequestBody AdminDTO loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Admin logged in successfully");

            return ResponseEntity.ok(response);

        } catch (Exception ex) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
