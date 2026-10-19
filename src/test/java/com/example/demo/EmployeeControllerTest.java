package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;
    
    @Test
    void getEmployeeNotFoundTest() throws Exception {

    	    when(employeeService.getEmployeeById(999))
    	            .thenThrow(new EmployeeNotFoundException("Employee not found"));

    	    mockMvc.perform(get("/employees/999"))
    	            .andExpect(status().isNotFound());
    }
    
    @Test
    void saveEmployeeInvalidEmailTest() throws Exception {

        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "name": "Rahul",
                        "email": "invalid-email"
                    }
                    """))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    void updateEmployeeTest() throws Exception {

        Employee updatedEmployee = new Employee();
        updatedEmployee.setId(1);
        updatedEmployee.setName("Amit");
        updatedEmployee.setEmail("amit@gmail.com");

        when(employeeService.updateEmployee(
                org.mockito.ArgumentMatchers.eq(1),
                org.mockito.ArgumentMatchers.any(Employee.class)))
                .thenReturn(updatedEmployee);

        mockMvc.perform(put("/employees/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                	    {
                	        "id": 1,
                	        "name": "Amit",
                	        "email": "amit@gmail.com"
                	    }
                	    """))
                .andExpect(status().isOk());
    }
    
    @Test
    void deleteEmployee() throws Exception {
    	
    	 doNothing().when(employeeService).deletedById(1);
    	 
    	 mockMvc.perform(delete("/employees/1"))
    	 .andExpect(status().isNoContent());
    }
    
    @Test
    void saveEmployeeTest() throws Exception {

        Employee savedEmployee = new Employee();
        savedEmployee.setId(1);
        savedEmployee.setName("Rahul");
        savedEmployee.setEmail("rahul@gmail.com");

        when(employeeService.saveEmployee(any(Employee.class)))
                .thenReturn(savedEmployee);

        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "name": "Rahul",
                        "email": "rahul@gmail.com"
                    }
                    """))
                .andExpect(status().isCreated());
    }
    
    @Test
    void employeeById() throws Exception {
    	Employee employee = new Employee();
    	employee.setId(1);
    	employee.setName("Rahul");
    	employee.setEmail("Rahul@gmail.com");
    	
    	when(employeeService.getEmployeeById(1))
     	.thenReturn(employee);
    	
    	mockMvc.perform(get("/employees/1"))
    	.andExpect(status().isOk());
    }
    
    @Test
    void getAllEmployeesTest() throws Exception {

        Pageable pageable = PageRequest.of(0, 5);

        Page<Employee> employeePage =
                new PageImpl<>(List.of(new Employee(), new Employee()), pageable, 2);

        when(employeeService.getAllEmployees(pageable))
                .thenReturn(employeePage);

        mockMvc.perform(get("/employees")
                .param("page", "0")
                .param("size", "5"))
                .andExpect(status().isOk());
    }
}
