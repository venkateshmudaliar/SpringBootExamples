package com.venky.EmployeesCRUD.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venky.EmployeesCRUD.exception.RecordNotFoundException;
import com.venky.EmployeesCRUD.model.EmployeeModel;
import com.venky.EmployeesCRUD.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
    EmployeeRepository repository;
     
    public List<EmployeeModel> getAllEmployees()
    {
        List<EmployeeModel> employeeList = repository.findAll();
         
        if(employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<EmployeeModel>();
        }
    }
    
    public EmployeeModel getEmployeeById(Integer id) throws RecordNotFoundException
    {
        Optional<EmployeeModel> employee = repository.findById(id);
         
        if(employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
    
    public void createOrUpdateEmployee(EmployeeModel entity) throws RecordNotFoundException
    {
    	repository.save(entity);
    	/*
        Optional<EmployeeModel> employee = repository.findById(entity.getId());
         
        if(employee.isPresent())
        {
        	EmployeeModel newEntity = employee.get();
            newEntity.setEmail(entity.getEmail());
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());
            newEntity.setLocation(entity.getLocation());
            newEntity = repository.save(newEntity);
             
            return newEntity;
        } else {
            entity = repository.save(entity);
             
            return entity;
        }
        */
    }
    
    public void deleteEmployeeById(Integer id) throws RecordNotFoundException
    {
        Optional<EmployeeModel> employee = repository.findById(id);
         
        if(employee.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

}
