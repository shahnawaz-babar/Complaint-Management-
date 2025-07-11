package com.complaint.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.complaint.management.entity.User;
import com.complaint.management.service.UserService;

@RequestMapping("/public")
@RestController
public class PublicController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/create-user")
	public ResponseEntity<?> userCreate(@RequestBody User user) {		
		userService.createNewUser(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	

}
