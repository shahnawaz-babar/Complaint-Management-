package com.complaint.management.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.complaint.management.entity.User;

public interface UserRepo extends MongoRepository<User,ObjectId>{

	void deleteByUserName(String userName);
	Optional<User> findByUserName(String userName);

	
}
