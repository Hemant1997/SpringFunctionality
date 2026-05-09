package com.main.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.entities.Employee;
import com.main.entities.User;
import com.main.repositries.EmployeeRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServices {
	
    @Autowired
	private EmployeeRepository erepo;
    
    @Autowired
    private UserService userService;

	@Transactional
	public void addEmployee(Employee employee, String username) {

		try{
			User user=userService.findUserByUserName(username);
			Employee e=erepo.save(employee);
			user.getList().add(e);
			userService.saveUser(user);

		}
		catch (Exception e){
			System.out.println(e);
			throw  new RuntimeException("something went wrong");
		}

	}

	public List<Employee >getAll() {
		// TODO Auto-generated method stub
		return erepo.findAll();
	}
	
	public Optional<Employee> findEmployeeById(ObjectId id) {
		return erepo.findById(id);
	}

	public void deleteEmployeeById(ObjectId myid, String username) {
	   User user= userService.findUserByUserName(username);
	   user.getList().removeIf(x->x.getId().equals(myid));
	   userService.saveUser(user);
	   erepo.deleteById(myid);
	}

	public void addEmployee(Employee emp) {
		erepo.save(emp);
		
	}

	
}
