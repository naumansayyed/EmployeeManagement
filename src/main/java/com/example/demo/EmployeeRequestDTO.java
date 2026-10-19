package com.example.demo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmployeeRequestDTO {
	
     @NotBlank(message = "Name is required")
	 private String name;
     
     @NotBlank(message = "Email is required")
     @Email(message = "Please enter valid email")
     private String email;
	 
	 public void setName(String name) {
		 this.name = name;
	 }
	 
	 public String getName() {
		return name; 
	 }
	 
	 public void setEmail(String email) {
		 this.email = email;
	 }
	 
	 public String getEmail() {
		 return email;
	 }
}
