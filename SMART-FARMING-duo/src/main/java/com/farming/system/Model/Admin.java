package com.farming.system.Model;

import jakarta.persistence.Entity;

@Entity
public class Admin extends User {
    private String role = "ADMIN"; 

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setRole(String role) {
        this.role = "ADMIN";  
    }
}
