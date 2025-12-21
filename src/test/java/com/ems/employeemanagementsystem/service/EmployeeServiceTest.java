package com.ems.employeemanagementsystem.service;

import com.ems.employeemanagementsystem.dto.CreateEmployeeRequest;
import com.ems.employeemanagementsystem.dto.UpdateEmployeeRequest;
import com.ems.employeemanagementsystem.model.Employee;
import com.ems.employeemanagementsystem.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;
    private CreateEmployeeRequest createRequest;
    private UpdateEmployeeRequest updateRequest;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .emailId("john.doe@example.com")
                .department("Engineering")
                .designation("Software Developer")
                .salary(75000.0)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        createRequest = CreateEmployeeRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .emailId("john.doe@example.com")
                .department("Engineering")
                .designation("Software Developer")
                .salary(75000.0)
                .build();

        updateRequest = UpdateEmployeeRequest.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe Updated")
                .emailId("john.doe.updated@example.com")
                .department("Engineering")
                .designation("Senior Software Developer")
                .salary(85000.0)
                .build();
    }

    @Test
    void createEmp_Success() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee result = employeeService.createEmp(createRequest);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("john.doe@example.com", result.getEmailId());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void getEmps_Success() {
        List<Employee> employees = Collections.singletonList(employee);
        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getEmps();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void getEmp_Success() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Employee result = employeeService.getEmp(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John", result.getFirstName());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void getEmp_NotFound() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> employeeService.getEmp(999L));
        verify(employeeRepository, times(1)).findById(999L);
    }

    @Test
    void updateEmp_Success() {
        Employee existingEmployee = Employee.builder()
                .id(1L)
                .firstName("Old")
                .lastName("Name")
                .emailId("old@example.com")
                .department("Old Dept")
                .designation("Old Designation")
                .salary(50000.0)
                .build();

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee result = employeeService.updateEmp(updateRequest);

        assertNotNull(result);
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void updateEmp_NotFound() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> employeeService.updateEmp(updateRequest));
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    void deleteEmp_Success() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        doNothing().when(employeeRepository).deleteById(1L);

        Employee result = employeeService.deleteEmp(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteEmp_NotFound() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> employeeService.deleteEmp(999L));
        verify(employeeRepository, times(1)).findById(999L);
        verify(employeeRepository, never()).deleteById(anyLong());
    }
}
