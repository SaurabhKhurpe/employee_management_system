package com.ems.employeemanagementsystem.controller;

import java.util.List;

import jakarta.validation.Valid;

import com.ems.employeemanagementsystem.dto.CreateEmployeeRequest;
import com.ems.employeemanagementsystem.dto.UpdateEmployeeRequest;
import com.ems.employeemanagementsystem.service.EmployeeService;
import com.ems.employeemanagementsystem.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	// create employee
	@PostMapping("/create")
	public MappingJacksonValue createEmployee(@RequestBody @Valid CreateEmployeeRequest createEmployeeRequest) {
		Employee employee = employeeService.createEmp(createEmployeeRequest);
		return filterNullValues(employee);
	}

	// get all employees
	@GetMapping
	public MappingJacksonValue getAllEmployees(){
		List<Employee> employees = employeeService.getEmps();
		return filterNullValues(employees);
	}

	// get an employee by ID
	@GetMapping("/{id}")
	public MappingJacksonValue getEmployeeById(@PathVariable("id") Long id) {
		Employee employee = employeeService.getEmp(id);
		return filterNullValues(employee);
	}

	// update an employee
	@PutMapping("/update")
	public MappingJacksonValue updateEmployee(@RequestBody @Valid UpdateEmployeeRequest updateEmployeeRequest){
		Employee employee = employeeService.updateEmp(updateEmployeeRequest);
		return filterNullValues(employee);
	}

	// delete an employee
	@DeleteMapping("/delete/{id}")
	public MappingJacksonValue deleteEmployee(@PathVariable("id") Long id){
		Employee employee = employeeService.deleteEmp(id);
		return filterNullValues(employee);
	}

	// Helper method to filter null values from response
	private MappingJacksonValue filterNullValues(Object object) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept();
		FilterProvider filters = new SimpleFilterProvider().addFilter("employeeFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(object);
		mapping.setFilters(filters);
		return mapping;
	}
}