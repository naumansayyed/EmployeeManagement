
package JUnitTestCase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.example.demo.Employee;
import com.example.demo.EmployeeNotFoundException;
import com.example.demo.EmployeeRepository;
import com.example.demo.EmployeeService;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

     @Test
     void getAllEmployeesTest() {
    Employee emp1 = new Employee();
    emp1.setName("Rahul");
    emp1.setEmail("rahul@gmail.com");

    Employee emp2 = new Employee();
    emp2.setName("Amit");
    emp2.setEmail("amit@gmail.com");

    PageRequest pageable = PageRequest.of(0, 2);

    Page<Employee> employeePage = new PageImpl<>(List.of(emp1, emp2), pageable, 2);

    when(employeeRepository.findAll(pageable))
            .thenReturn(employeePage);

    Page<Employee> result = employeeService.getAllEmployees(pageable);

    assertEquals(2, result.getContent().size());
    assertEquals("Rahul", result.getContent().get(0).getName());
    assertEquals("Amit", result.getContent().get(1).getName());

    verify(employeeRepository).findAll(pageable);
}

    @Test
    void searchByNameTest() {
    Employee employee = new Employee();
    employee.setName("Rahul");
    employee.setEmail("rahul@gmail.com");

    when(employeeRepository.findByName("Rahul"))
            .thenReturn(List.of(employee));

    List<Employee> result = employeeService.searchByName("Rahul");

    assertEquals(1, result.size());
    assertEquals("Rahul", result.get(0).getName());

    verify(employeeRepository).findByName("Rahul");
}

     @Test
      void deleteEmployeeTest() {
    employeeService.deletedById(101);

    verify(employeeRepository).deleteById(101);
}

    @Test
     void updateEmployeeTest() {
    Employee existingEmployee = new Employee();
    existingEmployee.setName("Rahul");
    existingEmployee.setEmail("rahul@gmail.com");

    Employee updatedDetails = new Employee();
    updatedDetails.setName("Amit");
    updatedDetails.setEmail("amit@gmail.com");

    when(employeeRepository.findById(101))
            .thenReturn(Optional.of(existingEmployee));

    when(employeeRepository.save(existingEmployee))
            .thenReturn(existingEmployee);

    Employee result =
            employeeService.updateEmployee(101, updatedDetails);

    assertEquals("Amit", result.getName());
    assertEquals("amit@gmail.com", result.getEmail());

    verify(employeeRepository).findById(101);
    verify(employeeRepository).save(existingEmployee);
}

    @Test
    void getEmployeeByIdNotFoundTest() {
    when(employeeRepository.findById(999))
            .thenReturn(Optional.empty());

    assertThrows(
        EmployeeNotFoundException.class,
        () -> employeeService.getEmployeeById(999)
    );

    verify(employeeRepository).findById(999);
}


    @Test
    void saveEmployeeTest() {
    Employee employee = new Employee();
    employee.setName("Rahul");
    employee.setEmail("rahul@gmail.com");

    when(employeeRepository.save(employee)).thenReturn(employee);

    Employee savedEmployee = employeeService.saveEmployee(employee);

    assertEquals("Rahul", savedEmployee.getName());
    assertEquals("rahul@gmail.com", savedEmployee.getEmail());

    verify(employeeRepository).save(employee);
}

    @Test
    void getEmployeeByIdTest() {
    Employee employee = new Employee();
    employee.setName("Rahul");
    employee.setEmail("rahul@gmail.com");

    when(employeeRepository.findById(101))
            .thenReturn(Optional.of(employee));

    Employee result = employeeService.getEmployeeById(101);

    assertEquals("Rahul", result.getName());
    assertEquals("rahul@gmail.com", result.getEmail());

    verify(employeeRepository).findById(101);
}

}
