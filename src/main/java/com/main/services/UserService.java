package com.main.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.repositries.UserRepository;
import com.main.entities.Employee;
import com.main.entities.User;

@Service
public class UserService {

	 @Autowired
	 private UserRepository userRepo;
	 
	 public boolean saveUser(User user) {
		User e= userRepo.save(user);
		if(e!=null) {
			return true;
		}
		return false;
	 }

	public List<User> getAll() {
		// TODO Auto-generated method stub
		List<User> list=userRepo.findAll();
		return list;
	}

	public Optional<User> findEmployeeById(ObjectId id) {
		Optional<User> user=userRepo.findById(id);
		return user;
	}
	
	public User findUserByUserName(String name) {
	 User user=	userRepo.findUserByUsername(name);
	return user;
	}

	 
    
}
