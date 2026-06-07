package com.main.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	
	 @GetMapping("/getAll")
	    public ResponseEntity<?> getAllEmployee(){
	    	List<User> list=userService.getAll();
	    	if(!list.isEmpty()) {
	    		return new ResponseEntity<>(list,HttpStatus.OK);
	    	}
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	@PutMapping("/update")
	public ResponseEntity<?> findEmployeeById(@RequestBody User user) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User user2 = userService.findUserByUserName(username); // use logged-in user, not request body
		if (user2 != null) {
			user2.setUsername(user.getUsername());
			// only re-encode if a new password is provided
			if (user.getPassword() != null && !user.getPassword().isEmpty()) {
				user2.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			userService.updateUser(user2); // ← use new method
		}
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}


	@PostMapping("/create")
	public ResponseEntity<?> createUser(@RequestBody User user) {

		boolean created = userService.saveUser(user);

		if (created) {
			return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
		}

		return new ResponseEntity<>("Failed to create user", HttpStatus.BAD_REQUEST);
	}
}
