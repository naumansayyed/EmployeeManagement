package com.example.demo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class EmployeeController {

	   public final EmployeeService employeeService;
	   
	   public EmployeeController(EmployeeService employeeService) {
		   this.employeeService = employeeService;
	   }
	   
	   @GetMapping("/employees")
	   public Page<Employee> getAllEmployees(Pageable pageable) {
		   return employeeService.getAllEmployees(pageable);
	   }
	   
	   @PostMapping("/employees")
	   public ResponseEntity<Employee> 
	          saveEmployee(@RequestBody @Valid EmployeeRequestDTO employeeRequestDTO) {
		   
		   Employee emp = new Employee();
		   
		   emp.setName(employeeRequestDTO.getName());
		   emp.setEmail(employeeRequestDTO.getEmail());
		   
		   Employee savedEmployee = employeeService.saveEmployee(emp);
		   
		   return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
	   }
	   
	   @GetMapping("/employees/{id}")
	   public Employee getEmployeeById(@PathVariable Integer id) {
		   return employeeService.getEmployeeById(id);
	   }
	   
	   @PutMapping("/employees/{id}")
	   public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id,
			                          @RequestBody Employee employee) {
		   Employee updateEmployee = employeeService.updateEmployee(id, employee);
		 return new ResponseEntity<> (updateEmployee, HttpStatus.OK);
	   }
	   
	   @DeleteMapping("/employees/{id}")
	   public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
		    employeeService.deletedById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   }
	   
	   @GetMapping("/employees/search") 
	   public List<Employee> searchEmployee(@RequestParam String name) {
		      return employeeService.searchByName(name);
	   }
}
