package com.complaint.management.service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.complaint.management.entity.User;
import com.complaint.management.repository.UserRepo;

@Component
public class UserService {

	@Autowired
	private UserRepo userRepo;

	public void createUser(User user) {
		userRepo.save(user);
	}

	public List<User> getUsers() {
		return userRepo.findAll();
	}

	public ResponseEntity<?> deleteUser(String userName) {
		User user = userRepo.findByUserName(userName).orElse(null);
		if (user != null) {
			userRepo.deleteByUserName(userName);
			return new ResponseEntity<>(user, HttpStatus.NO_CONTENT);
		} else {
			System.out.println("User not exist");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public Optional<User> findUserName(String userName) {
		return userRepo.findByUserName(userName);
	}

	public ResponseEntity<?> updateUser(User newEntry, String userName) {
		User old = userRepo.findByUserName(userName).orElse(null);
		if (old != null) {

			old.setPassword(newEntry.getPassword() != null && !newEntry.getPassword().isEmpty() ? newEntry.getPassword()
					: old.getPassword());
			old.setUserName(newEntry.getUserName() != null && !newEntry.getUserName().isBlank() ? newEntry.getUserName()
					: old.getUserName());
			userRepo.save(old);
			return new ResponseEntity<>(old, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
