package com.main.entities;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;
import java.util.List;
import java.util.ArrayList;


@Document(collection="users")
@Data
public class User {

	@Id
	private ObjectId id;
	@Indexed(unique=true)
	@NonNull
	private String username;
	@NonNull
	private String password;
	
	private List<Employee> list=new ArrayList<>();
    private List<String> roles;



	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", list=" + list + "]";
	}
	
	
}