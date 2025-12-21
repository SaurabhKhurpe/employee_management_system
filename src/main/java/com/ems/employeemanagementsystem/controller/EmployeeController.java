package com.ems.employeemanagementsystem.controller;

import java.util.List;

import jakarta.validation.Valid;

import com.ems.employeemanagementsystem.dto.CreateEmployeeRequest;
import com.ems.employeemanagementsystem.dto.UpdateEmployeeRequest;
import com.ems.employeemanagementsystem.service.EmployeeService;
import com.ems.employeemanagementsystem.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@Validated
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	// create employee
	@PostMapping("/create")
	public ResponseEntity<?> createEmployee(@RequestBody @Valid CreateEmployeeRequest createEmployeeRequest) {
		try {
			Employee employee = employeeService.createEmp(createEmployeeRequest);
			return ResponseEntity.ok(employee);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error creating employee: " + e.getMessage());
		}
	}

	// get all employees
	@GetMapping
	public ResponseEntity<?> getAllEmployees(){
		try {
			List<Employee> employees = employeeService.getEmps();
			return ResponseEntity.ok(employees);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error fetching employees: " + e.getMessage());
		}
	}

	// get an employee by ID
	@GetMapping("/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable("id") Long id) {
		try {
			Employee employee = employeeService.getEmp(id);
			return ResponseEntity.ok(employee);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Employee not found with id: " + id);
		}
	}

	// update an employee
	@PutMapping("/update")
	public ResponseEntity<?> updateEmployee(@RequestBody @Valid UpdateEmployeeRequest updateEmployeeRequest){
		try {
			Employee employee = employeeService.updateEmp(updateEmployeeRequest);
			return ResponseEntity.ok(employee);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error updating employee: " + e.getMessage());
		}
	}

	// delete an employee
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id){
		try {
			Employee employee = employeeService.deleteEmp(id);
			return ResponseEntity.ok(employee);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Error deleting employee: " + e.getMessage());
		}
	}
}