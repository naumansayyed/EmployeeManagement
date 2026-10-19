package com.example.demo;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

	public static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	
	private final EmployeeRepository employeeRepository;
	
	  // Constructor Injection
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	 
	 //Create
	public Employee saveEmployee(Employee employee) {
		Employee savedEmployee = employeeRepository.save(employee);
		logger.info("Employee saved successfully: {}", savedEmployee.getName());
				return savedEmployee;
	}
	
	 // READ ALL
	public Page<Employee> getAllEmployees(Pageable pageable) {
		return employeeRepository.findAll(pageable);
	}
	
	public List<Employee> searchByName(String name) {
		return employeeRepository.findByName(name);
	}
	
	 // READ ONE
	public Employee getEmployeeById(Integer id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		
		if(employee.isPresent()) {
			return employee.get();
		}
		
		throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
	}
	
    // DELETE
	public void deletedById(Integer id) {
		 employeeRepository.deleteById(id);
	}
	
	public Employee updateEmployee(Integer id, Employee employee) {
		Optional<Employee> existing = employeeRepository.findById(id);
		
		if(existing.isPresent()) {
			Employee emp = existing.get();
			
			emp.setName(employee.getName());
			emp.setEmail(employee.getEmail());
			
			return employeeRepository.save(emp);
			
		}
		
		return null;
	}
}
