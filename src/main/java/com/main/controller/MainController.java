package com.main.controller;


import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.main.entities.Employee;
import com.main.services.EmployeeServices;

@RestController
@RequestMapping("/Employee")
public class MainController {
    
	
	@Autowired
	private EmployeeServices employeeService;
	
	
    @PostMapping("/add")
    public boolean addEmployee(@RequestBody Employee employee) {
    	employee.setDate(LocalDateTime.now());
    	return employeeService.addEmployee(employee);
    }
   
    
}
