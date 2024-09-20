package com.farming.system.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farming.system.Model.Admin;


public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);
}