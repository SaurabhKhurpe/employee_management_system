package com.ems.employeemanagementsystem.controller;

import com.ems.employeemanagementsystem.dto.CreateEmployeeRequest;
import com.ems.employeemanagementsystem.dto.UpdateEmployeeRequest;
import com.ems.employeemanagementsystem.model.Employee;
import com.ems.employeemanagementsystem.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
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
    void createEmployee_Success() throws Exception {
        when(employeeService.createEmp(any(CreateEmployeeRequest.class))).thenReturn(employee);

        mockMvc.perform(post("/employees/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("John")))
                .andExpect(jsonPath("$.lastName", is("Doe")))
                .andExpect(jsonPath("$.emailId", is("john.doe@example.com")));

        verify(employeeService, times(1)).createEmp(any(CreateEmployeeRequest.class));
    }

    @Test
    void createEmployee_ValidationError() throws Exception {
        CreateEmployeeRequest invalidRequest = CreateEmployeeRequest.builder()
                .firstName("") // Empty first name
                .lastName("")
                .emailId("")
                .build();

        mockMvc.perform(post("/employees/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());

        verify(employeeService, never()).createEmp(any(CreateEmployeeRequest.class));
    }

    @Test
    void getAllEmployees_Success() throws Exception {
        List<Employee> employees = Collections.singletonList(employee);
        when(employeeService.getEmps()).thenReturn(employees);

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is("John")));

        verify(employeeService, times(1)).getEmps();
    }

    @Test
    void getEmployeeById_Success() throws Exception {
        when(employeeService.getEmp(1L)).thenReturn(employee);

        mockMvc.perform(get("/employees/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("John")))
                .andExpect(jsonPath("$.emailId", is("john.doe@example.com")));

        verify(employeeService, times(1)).getEmp(1L);
    }

    @Test
    void updateEmployee_Success() throws Exception {
        when(employeeService.updateEmp(any(UpdateEmployeeRequest.class))).thenReturn(employee);

        mockMvc.perform(put("/employees/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("John")));

        verify(employeeService, times(1)).updateEmp(any(UpdateEmployeeRequest.class));
    }

    @Test
    void deleteEmployee_Success() throws Exception {
        when(employeeService.deleteEmp(1L)).thenReturn(employee);

        mockMvc.perform(delete("/employees/delete/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("John")));

        verify(employeeService, times(1)).deleteEmp(1L);
    }
}