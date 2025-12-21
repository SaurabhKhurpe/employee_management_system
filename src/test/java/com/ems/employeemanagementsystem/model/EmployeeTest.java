package com.ems.employeemanagementsystem.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void employeeBuilder_Success() {
        LocalDateTime now = LocalDateTime.now();

        Employee employee = Employee.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .emailId("john.doe@example.com")
                .department("Engineering")
                .designation("Software Developer")
                .salary(75000.0)
                .createdAt(now)
                .updatedAt(now)
                .build();

        assertNotNull(employee);
        assertEquals(1L, employee.getId());
        assertEquals("John", employee.getFirstName());
        assertEquals("Doe", employee.getLastName());
        assertEquals("john.doe@example.com", employee.getEmailId());
        assertEquals("Engineering", employee.getDepartment());
        assertEquals("Software Developer", employee.getDesignation());
        assertEquals(75000.0, employee.getSalary());
        assertEquals(now, employee.getCreatedAt());
        assertEquals(now, employee.getUpdatedAt());
    }

    @Test
    void employeeSettersAndGetters_Success() {
        Employee employee = new Employee();
        LocalDateTime now = LocalDateTime.now();

        employee.setId(1L);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmailId("john.doe@example.com");
        employee.setDepartment("Engineering");
        employee.setDesignation("Developer");
        employee.setSalary(75000.0);
        employee.setCreatedAt(now);
        employee.setUpdatedAt(now);

        assertEquals(1L, employee.getId());
        assertEquals("John", employee.getFirstName());
        assertEquals("Doe", employee.getLastName());
        assertEquals("john.doe@example.com", employee.getEmailId());
        assertEquals("Engineering", employee.getDepartment());
        assertEquals("Developer", employee.getDesignation());
        assertEquals(75000.0, employee.getSalary());
        assertEquals(now, employee.getCreatedAt());
        assertEquals(now, employee.getUpdatedAt());
    }

    @Test
    void employeeNoArgsConstructor_Success() {
        Employee employee = new Employee();

        assertNotNull(employee);
        assertNull(employee.getFirstName());
        assertNull(employee.getLastName());
        assertNull(employee.getEmailId());
    }

    @Test
    void employeeAllArgsConstructor_Success() {
        LocalDateTime now = LocalDateTime.now();

        Employee employee = new Employee(1L, "John", "Doe", "john.doe@example.com",
                "Engineering", "Developer", 75000.0, now, now);

        assertEquals(1L, employee.getId());
        assertEquals("John", employee.getFirstName());
        assertEquals("Doe", employee.getLastName());
        assertEquals("john.doe@example.com", employee.getEmailId());
        assertEquals("Engineering", employee.getDepartment());
        assertEquals("Developer", employee.getDesignation());
        assertEquals(75000.0, employee.getSalary());
        assertEquals(now, employee.getCreatedAt());
        assertEquals(now, employee.getUpdatedAt());
    }

    @Test
    void employeeToString_Success() {
        Employee employee = Employee.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .emailId("john.doe@example.com")
                .build();

        String toString = employee.toString();

        assertNotNull(toString);
        assertTrue(toString.contains("John"));
        assertTrue(toString.contains("Doe"));
        assertTrue(toString.contains("john.doe@example.com"));
    }
}