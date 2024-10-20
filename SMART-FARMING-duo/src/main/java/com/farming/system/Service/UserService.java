package com.farming.system.Service;

import com.farming.system.Model.User;
import com.farming.system.Exception.UserAlreadyExistsException; // Custom Exception
import com.farming.system.Exception.InvalidCredentialsException; // Custom Exception
import com.farming.system.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	 @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private PasswordEncoder passwordEncoder;

	    // Register a new user
	    public User registerUser(User user) throws UserAlreadyExistsException {
	        // Check if the password matches
	        if (!user.getPassword().equals(user.getConfirmPassword())) {
	            throw new IllegalArgumentException("Passwords do not match");
	        }

	        // Check if the user already exists
	        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
	        if (existingUser.isPresent()) {
	            throw new UserAlreadyExistsException("User with email already exists");
	        }

	        // Encode password and save user
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        return userRepository.save(user);
	    }

	    // Authenticate user for login
	    public User loginUser(String username, String password) throws InvalidCredentialsException {
	        Optional<User> userOpt = userRepository.findByUsername(username);

	        if (userOpt.isPresent()) {
	            User user = userOpt.get();
	            if (passwordEncoder.matches(password, user.getPassword())) {
	                return user;
	            } else {
	                throw new InvalidCredentialsException("Invalid credentials");
	            }
	        } else {
	            throw new InvalidCredentialsException("User not found");
	        }
	    }
    
 // Create a new user
    public User createUser(User user) {
        // Check if username exists
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Encode the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    // Update a user
    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setFullName(userDetails.getFullName());

            if (!userDetails.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
            }
            return userRepository.save(user);
        }).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    // Delete a user
    public boolean deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return true;
        } else {
            return false;
        }
    }
}
