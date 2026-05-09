package com.main.repositries;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.main.entities.User;

public interface UserRepository extends MongoRepository<User, ObjectId>{
	User findUserByUsername(String username);
}
