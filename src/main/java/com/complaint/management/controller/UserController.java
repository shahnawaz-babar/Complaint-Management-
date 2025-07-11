package com.complaint.management.controller;

import java.util.Arrays;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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

	@GetMapping
	public ResponseEntity<?> findByName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserName(authentication.getName()).orElse(null);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping
	public ResponseEntity<?> userDelete() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return userService.deleteUser(authentication.getName());
	}

	@PutMapping
	public ResponseEntity<?> userUpdate(@RequestBody User user) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return userService.updateUser(user, authentication.getName());
	}

}
