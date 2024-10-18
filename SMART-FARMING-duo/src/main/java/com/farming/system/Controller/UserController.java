package com.farming.system.Controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.farming.system.Model.User;
import com.farming.system.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody User user) {
        try {
            // Call the registerUser method in the service to register the user
            userService.registerUser(user);
            return ResponseEntity.ok(Map.of("message", "User registered successfully"));
        } catch (IllegalArgumentException e) {
            // Handle error if passwords don't match
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    // User login (POST request to /user/login)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        // Call the loginUser method in the service to log in the user
        User loggedInUser = userService.loginUser(user.getUsername(), user.getPassword());
        
        if (loggedInUser != null) {
            // Return the user info if login is successful
            return ResponseEntity.ok(loggedInUser);
        } else {
            // Return error if login fails
            return ResponseEntity.status(401).body(Map.of("message", "Invalid username or password"));
        }
    }
}
