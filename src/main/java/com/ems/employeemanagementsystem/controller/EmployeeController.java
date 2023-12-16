package com.ems.employeemanagementsystem.controller;

import java.util.*;

import javax.validation.Valid;

import com.ems.employeemanagementsystem.dto.CreateEmployeeRequest;
import com.ems.employeemanagementsystem.dto.UpdateEmployeeRequest;
import com.ems.employeemanagementsystem.service.EmployeeService;
import com.ems.employeemanagementsystem.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	// create employee
	@PostMapping("/create")
	public Employee createEmployee(@RequestBody @Valid CreateEmployeeRequest createEmployeeRequest) {
		return employeeService.createEmp(createEmployeeRequest);
	}

	// get all employees
	@GetMapping("/all")
	public List<Employee> getAllBooks(){
		return employeeService.getEmps();
	}

	// get an employee
	@GetMapping("/get")
	public Employee getEmployeeById(@RequestParam("empId") Long empId) {
		return employeeService.getEmp(empId);
	}

	// update an employee
	@PutMapping("/update")
	public Employee updateEmployee(@RequestBody UpdateEmployeeRequest updateEmployeeRequest){
		return employeeService.updateEmp(updateEmployeeRequest);
	}

	// delete an employee
	@DeleteMapping("/delete")
	public Employee deleteEmployee(@RequestParam("empId") Long empId){
		return employeeService.deleteEmp(empId);
	}
}

