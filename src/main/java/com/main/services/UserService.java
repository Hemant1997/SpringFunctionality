package com.main.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.main.repositries.UserRepository;
import com.main.entities.Employee;
import com.main.entities.User;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	// ✅ Constructor injection
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	 public boolean saveUser(User user) {
		 user.setPassword(passwordEncoder.encode(user.getPassword()));
		 user.setRoles(Arrays.asList("USER"));
		User e= userRepository.save(user);
		if(e!=null) {
			return true;
		}
		return false;
	 }

	public List<User> getAll() {
		// TODO Auto-generated method stub
		List<User> list=userRepository.findAll();
		return list;
	}

	public Optional<User> findEmployeeById(ObjectId id) {
		Optional<User> user=userRepository.findById(id);
		return user;
	}
	
	public User findUserByUserName(String name) {
	 User user=	userRepository.findUserByUsername(name);
	return user;
	}

	 
    
}
