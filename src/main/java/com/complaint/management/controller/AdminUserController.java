package com.complaint.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.complaint.management.entity.Complaint;
import com.complaint.management.entity.User;
import com.complaint.management.service.ComplaintService;
import com.complaint.management.service.UserService;

@RestController
@RequestMapping("/admin-user")
public class AdminUserController {
		
	@Autowired
	UserService userService;		
	
	@GetMapping("/get-all")
	public List<User> allUser() {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		return userService.getUsers();
	}
	
	@GetMapping
	public ResponseEntity<?> getUser()
	{
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
			User user=userService.findUserName(authentication.getName()).orElse(null);
			if(user!=null)
			{
				return new ResponseEntity<>(user,HttpStatus.FOUND);
			}
			else 
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	}
	

	
}
