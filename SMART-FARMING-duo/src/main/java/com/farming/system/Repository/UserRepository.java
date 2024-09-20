package com.farming.system.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farming.system.Model.User;



public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}