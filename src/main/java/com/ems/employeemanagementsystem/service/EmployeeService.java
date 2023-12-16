package com.ems.employeemanagementsystem.service;

import com.ems.employeemanagementsystem.dto.CreateEmployeeRequest;
import com.ems.employeemanagementsystem.dto.UpdateEmployeeRequest;
import com.ems.employeemanagementsystem.model.Employee;
import com.ems.employeemanagementsystem.repository.EmployeeRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee createEmp(CreateEmployeeRequest createEmployeeRequest){
        Employee employee = createEmployeeRequest.to(); // dto to model conversion
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmps() {
        return employeeRepository.findAll();
    }

    public Employee getEmp(Long empId) {
        return employeeRepository.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee with id: " + empId + " doesn't exist in database"));
    }

    public Employee updateEmp(UpdateEmployeeRequest updateEmployeeRequest) {
        Employee employee = updateEmployeeRequest.to();
        return employeeRepository.save(employee);
    }

    public Employee deleteEmp(Long empId) {
        Employee employee = employeeRepository.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee with id: " + empId + " doesn't exist in database"));
        employeeRepository.deleteById(empId);
        return employee;
    }
}