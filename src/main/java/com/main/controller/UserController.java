package com.main.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.entities.Employee;
import com.main.entities.User;
import com.main.repositries.UserRepository;
import com.main.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    
	@Autowired
	private UserService userService;
	
	@PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
    	Boolean result=userService.saveUser(user);
    	if(result) {
    		return new ResponseEntity<>(HttpStatus.OK);
    	}
    	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
	
	 @GetMapping("/getAll")
	    public ResponseEntity<?> getAllEmployee(){
	    	List<User> list=userService.getAll();
	    	if(!list.isEmpty()) {
	    		return new ResponseEntity<>(list,HttpStatus.OK);
	    	}
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	 
	 @PutMapping("/update")
	 public ResponseEntity<?> findEmployeeById(@RequestBody User user){
	 User user2=userService.findUserByUserName(user.getUsername());
		if(!user2.equals(null)) {
			user2.setUsername(user.getUsername());
			user2.setPassword(user.getPassword());
			userService.saveUser(user2);
		}
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	 }
	 

	
}
