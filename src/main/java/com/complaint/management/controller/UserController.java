package com.complaint.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.complaint.management.entity.User;
import com.complaint.management.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<?> userCreate(@RequestBody User user) {
		user.setRoles("USER");
		userService.createUser(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("{userName}")
	public ResponseEntity<?> findByName(@PathVariable String userName) {

		User user = userService.findUserName(userName).orElse(null);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	public List<User> allUser() {
		return userService.getUsers();
	}

	@DeleteMapping("{userName}")
	public ResponseEntity<?> userDelete(@PathVariable String userName) {
		return userService.deleteUser(userName);
	}

	// sirf ye kaam nhi kara or
	@PutMapping("{userName}")
	public ResponseEntity<?> userUpdate(@PathVariable String userName, @RequestBody User user) {
		return userService.updateUser(user, userName);
	}

}
