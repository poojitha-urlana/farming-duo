package com.farming.system.Dto;

public class UserDTO {
    public UserDTO() {
		super();
		
	}
	public UserDTO(String username, String email, String role) {
		super();
		this.username = username;
		this.email = email;
		this.role = role;
	}
	private String username;
    private String email;
    private String role;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
    
   
}
