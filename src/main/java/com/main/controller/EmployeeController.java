package com.main.controller;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.main.entities.Employee;
import com.main.entities.User;
import com.main.services.EmployeeServices;
import com.main.services.UserService;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {
    
	
	@Autowired
	private EmployeeServices employeeService;
	
	@Autowired
	private UserService userService;
	
	
    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
    	try {
			Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
			String username=authentication.getName();
    		employee.setDate(LocalDateTime.now());
    		
    		employeeService.addEmployee(employee,username);
        	return new ResponseEntity<>(employee,HttpStatus.CREATED);	
    	}catch(Exception e) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    }
   
    @GetMapping
    public ResponseEntity<?> getAllEmployeeByUsername(){
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		String username=authentication.getName();
      User user=userService.findUserByUserName(username);
    	List<Employee> list=user.getList();
    	if(!list.isEmpty()) {
    		return new ResponseEntity<>(list,HttpStatus.OK);
    	}
    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/id/{myid}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable ObjectId myid) {
    	Optional<Employee> emp=employeeService.findEmployeeById(myid);
    	if(emp.isPresent()) {
    		return new ResponseEntity<>(emp.get(),HttpStatus.OK);
    	}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	
    }
    
    @DeleteMapping("/id/{username}/{myid}")
    public ResponseEntity<?> removeEmployeeById(@PathVariable String myid,@PathVariable String username) {
    	
            ObjectId id = new ObjectId(myid);
            employeeService.deleteEmployeeById(id,username);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        
    }
   
    @PutMapping("/id/{username}/{myid}")
    public ResponseEntity<?> updateEmployeeById(@PathVariable ObjectId myid,@RequestBody Employee emp) {
    	Employee old=employeeService.findEmployeeById(myid).orElse(null);
    	if(old!=null) {
    		old.setName(emp.getName()!=null && !emp.getName().equals("")?emp.getName():old.getName());
    		old.setSalary(emp.getSalary()!=0 && emp.getSalary()>0?emp.getSalary():old.getSalary());
    		employeeService.addEmployee(old);
    		return new ResponseEntity<>(old,HttpStatus.OK);
    	}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	
    }
}
