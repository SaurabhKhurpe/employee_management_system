package com.ems.employeemanagementsystem.service;

import com.ems.employeemanagementsystem.dto.CreateEmployeeRequest;
import com.ems.employeemanagementsystem.dto.UpdateEmployeeRequest;
import com.ems.employeemanagementsystem.model.Employee;
import com.ems.employeemanagementsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee createEmp(CreateEmployeeRequest createEmployeeRequest){
        Employee employee = createEmployeeRequest.to();
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmps() {
        return employeeRepository.findAll();
    }

    public Employee getEmp(Long empId) {
        return employeeRepository.findById(empId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Employee with id: " + empId + " doesn't exist in database"
                ));
    }

    public Employee updateEmp(UpdateEmployeeRequest updateEmployeeRequest) {
        Employee existingEmployee = employeeRepository.findById(updateEmployeeRequest.getId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Employee with id: " + updateEmployeeRequest.getId() + " doesn't exist in database"
                ));

        existingEmployee.setFirstName(updateEmployeeRequest.getFirstName());
        existingEmployee.setLastName(updateEmployeeRequest.getLastName());
        existingEmployee.setEmailId(updateEmployeeRequest.getEmailId());
        existingEmployee.setDepartment(updateEmployeeRequest.getDepartment());
        existingEmployee.setDesignation(updateEmployeeRequest.getDesignation());
        existingEmployee.setSalary(updateEmployeeRequest.getSalary());

        return employeeRepository.save(existingEmployee);
    }

    public Employee deleteEmp(Long empId) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Employee with id: " + empId + " doesn't exist in database"
                ));
        employeeRepository.deleteById(empId);
        return employee;
    }
}