package com.farming.system.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.farming.system.Model.User;
import com.farming.system.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Method to register a user
    public User registerUser(User user) {
        // Check if passwords match
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        // Set user role to "USER"
        user.setRole("USER");

        // Encode (encrypt) the password before saving it
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user in the database
        return userRepository.save(user);
    }

    // Method for user login
    public User loginUser(String username, String password) {
        // Find user by username
        User user = userRepository.findByUsername(username);

        // Check if user exists and password matches
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // Set password to null to not return it (for safety)
            user.setPassword(null);
            return user;
        }
        return null; // If no user or password doesn't match
    }

    // Create a new user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get a user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Update a user by ID
    public User updateUser(Long id, User updatedUser) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            return userRepository.save(existingUser);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    // Delete a user by ID
    public void deleteUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }
}
