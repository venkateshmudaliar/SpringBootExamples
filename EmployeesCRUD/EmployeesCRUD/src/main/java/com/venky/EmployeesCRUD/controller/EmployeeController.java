package com.venky.EmployeesCRUD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.venky.EmployeesCRUD.exception.RecordNotFoundException;
import com.venky.EmployeesCRUD.model.EmployeeModel;
import com.venky.EmployeesCRUD.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
    EmployeeService service;
	
	@GetMapping("/employees")
    public List<EmployeeModel> getAllEmployees() {
        List<EmployeeModel> list = service.getAllEmployees();
		return list;
	}
	
	@GetMapping("/employees/{id}")
    public EmployeeModel getEmployeeById(@PathVariable("id") Integer id) throws RecordNotFoundException {
		EmployeeModel entity = service.getEmployeeById(id);
        return entity;
    }
	
	@PostMapping("/employees")
	public List<EmployeeModel> createOrUpdateEmployee( @RequestBody EmployeeModel employee) throws RecordNotFoundException {
		service.createOrUpdateEmployee(employee);
		
		return service.getAllEmployees();
	}
	
	@DeleteMapping("/employees/{id}")
	public List<EmployeeModel> deleteEmployeeById(@PathVariable("id") Integer id) throws RecordNotFoundException {
	    service.deleteEmployeeById(id);
	    return service.getAllEmployees();
	}

}
