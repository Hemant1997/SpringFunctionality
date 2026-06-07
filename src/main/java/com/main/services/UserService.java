package com.main.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.main.repositries.UserRepository;
import com.main.entities.Employee;
import com.main.entities.User;

@Service
@Slf4j
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;


	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	public boolean saveUser(User user) {
		try {
			log.info("Attempting to save user: {}", user.getUsername());

			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRoles(Arrays.asList("USER"));

			User savedUser = userRepository.save(user);

			return savedUser != null;

		} catch (Exception e) {
			log.error("Error while saving user: {}", user.getUsername(), e);
			log.warn("tring warn");
			log.info("tring info");
			log.trace("trying trace" );
			return false;
		}
	}

	// NEW METHOD — for updates, password is already encoded
	public boolean updateUser(User user) {
		User e = userRepository.save(user);
		return e != null;
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
	 User user=	userRepository.findByUsername(name);
	return user;
	}


	public boolean saveNewUser(User user) {
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRoles(Arrays.asList("USER"));

			userRepository.save(user);
			return true;

		} catch (Exception e) {
			log.error("Something went wrong while saving user", e);
			return false;
		}

	}
}
