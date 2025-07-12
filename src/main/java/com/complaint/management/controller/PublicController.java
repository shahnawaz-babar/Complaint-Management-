package com.complaint.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.complaint.management.entity.User;
import com.complaint.management.service.UserDetailsServiceImpl;
import com.complaint.management.service.UserService;
import com.complaint.management.utilis.JwtUtil;

@RequestMapping("/public")
@RestController
public class PublicController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/sign-up")
	public ResponseEntity<?> userCreate(@RequestBody User user) {		
		userService.createNewUser(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user)
	{
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
		UserDetails userDetails=userDetailsServiceImpl.loadUserByUsername(user.getUserName());
		if(userDetails!=null)
		{
			String jwt=jwtUtil.generateToken(userDetails.getUsername());
			return new ResponseEntity<>(jwt,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
