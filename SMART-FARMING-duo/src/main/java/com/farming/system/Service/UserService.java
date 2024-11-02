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

	    
	    public User registerUser(User user) throws UserAlreadyExistsException {
	        
	        if (!user.getPassword().equals(user.getConfirmPassword())) {
	            throw new IllegalArgumentException("Passwords do not match");
	        }

	        
	        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
	        if (existingUser.isPresent()) {
	            throw new UserAlreadyExistsException("User with name already exists");
	        }

	        
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        return userRepository.save(user);
	    }

	   
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
    

    public User createUser(User user) {
       
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

 
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
