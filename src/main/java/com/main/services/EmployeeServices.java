package com.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.entities.Employee;
import com.main.repositries.EmployeeRepository;

@Service
public class EmployeeServices {
	
    @Autowired
	private EmployeeRepository erepo;
	
	public boolean addEmployee(Employee employee) {
		Employee e=erepo.save(employee);
		if(e!=null)
			return true;
		return false;
	}
}
