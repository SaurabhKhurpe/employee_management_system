package com.ems.employeemanagementsystem.dto;

import com.ems.employeemanagementsystem.model.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DTOsTest {

    @Test
    void createEmployeeRequest_Success() {
        CreateEmployeeRequest request = CreateEmployeeRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .emailId("john.doe@example.com")
                .department("Engineering")
                .designation("Developer")
                .salary(75000.0)
                .build();

        Employee employee = request.to();

        assertNotNull(employee);
        assertEquals("John", employee.getFirstName());
        assertEquals("Doe", employee.getLastName());
        assertEquals("john.doe@example.com", employee.getEmailId());
        assertEquals("Engineering", employee.getDepartment());
        assertEquals("Developer", employee.getDesignation());
        assertEquals(75000.0, employee.getSalary());
    }

    @Test
    void updateEmployeeRequest_Success() {
        UpdateEmployeeRequest request = UpdateEmployeeRequest.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .emailId("john.doe@example.com")
                .department("Engineering")
                .designation("Senior Developer")
                .salary(85000.0)
                .build();

        Employee employee = request.to();

        assertNotNull(employee);
        assertEquals(1L, employee.getId());
        assertEquals("John", employee.getFirstName());
        assertEquals("Doe", employee.getLastName());
        assertEquals("john.doe@example.com", employee.getEmailId());
        assertEquals("Engineering", employee.getDepartment());
        assertEquals("Senior Developer", employee.getDesignation());
        assertEquals(85000.0, employee.getSalary());
    }
}