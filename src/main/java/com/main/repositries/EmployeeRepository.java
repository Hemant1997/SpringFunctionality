package com.main.repositries;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.main.entities.*;


@Repository
public interface EmployeeRepository extends MongoRepository<Employee,ObjectId>{

}
