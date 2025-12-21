package com.ems.employeemanagementsystem.repository;

import com.ems.employeemanagementsystem.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.datasource.driver-class-name=org.h2.Driver"
})
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void saveEmployee_Success() {
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .emailId("john.doe@example.com")
                .department("Engineering")
                .designation("Software Developer")
                .salary(75000.0)
                .build();

        Employee savedEmployee = employeeRepository.save(employee);

        assertEquals("John", savedEmployee.getFirstName());
        assertEquals("john.doe@example.com", savedEmployee.getEmailId());
        assertNotNull(savedEmployee.getCreatedAt());
        assertNotNull(savedEmployee.getUpdatedAt());
    }

    @Test
    void findById_Success() {
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .emailId("john.doe@example.com")
                .build();

        Employee savedEmployee = employeeRepository.save(employee);

        Optional<Employee> foundEmployee = employeeRepository.findById(savedEmployee.getId());

        assertTrue(foundEmployee.isPresent());
        assertEquals("John", foundEmployee.get().getFirstName());
    }

    @Test
    void findById_NotFound() {
        Optional<Employee> foundEmployee = employeeRepository.findById(999L);

        assertFalse(foundEmployee.isPresent());
    }

    @Test
    void findAll_Success() {
        Employee employee1 = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .emailId("john.doe@example.com")
                .build();

        Employee employee2 = Employee.builder()
                .firstName("Jane")
                .lastName("Smith")
                .emailId("jane.smith@example.com")
                .build();

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        List<Employee> employees = employeeRepository.findAll();

        assertEquals(2, employees.size());
    }

    @Test
    void deleteById_Success() {
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .emailId("john.doe@example.com")
                .build();

        Employee savedEmployee = employeeRepository.save(employee);

        employeeRepository.deleteById(savedEmployee.getId());

        Optional<Employee> deletedEmployee = employeeRepository.findById(savedEmployee.getId());

        assertFalse(deletedEmployee.isPresent());
    }

    @Test
    void updateEmployee_Success() {
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .emailId("john.doe@example.com")
                .department("Engineering")
                .build();

        Employee savedEmployee = employeeRepository.save(employee);

        savedEmployee.setDesignation("Senior Developer");
        savedEmployee.setSalary(85000.0);

        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        assertEquals("Senior Developer", updatedEmployee.getDesignation());
        assertEquals(85000.0, updatedEmployee.getSalary());
        assertNotNull(updatedEmployee.getUpdatedAt());
    }
}