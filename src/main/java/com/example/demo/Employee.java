package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Employee {
    
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq_gen")
	@SequenceGenerator(
			name = "employee_seq_gen",
			sequenceName = "employee_seq",
			allocationSize = 1)
   
   private int id;
   private String name;
   private String email;
   
   public Employee() {
   }
    public Employee(int id,String name,String email) {
    	          this.id = id;
    	          this.name = name;
    	          this.email = email;
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
}
