package com.farming.system.Model;

import jakarta.persistence.Entity;

@Entity
public class Admin extends User {

    
	 private String role = "ADMIN"; 
}
