package com.complaint.management.controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
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

import com.complaint.management.entity.Complaint;
import com.complaint.management.entity.User;
import com.complaint.management.service.ComplaintService;
import com.complaint.management.service.UserService;

@RequestMapping("/complaint")
@RestController
public class ComplaintController {

	@Autowired
	private ComplaintService complaintService;
	@Autowired
	private UserService userService;

	@GetMapping("{userName}")
	public ResponseEntity<?> ComplaintAll(@PathVariable String userName) {
		User user = userService.findUserName(userName).orElse(null);
		if (user != null) {
			List<Complaint> list = user.getComplaints();
			return new ResponseEntity<>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("{userName}")
	public ResponseEntity<?> compliantCreate(@RequestBody Complaint complaint, @PathVariable String userName) {
		User user = userService.findUserName(userName).orElse(null);
		if (user != null) {
			complaintService.createComplaint(complaint, user);
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// complaint get by Id
	@GetMapping("/id/{id}")
	public ResponseEntity<?> complaintgetById(@PathVariable ObjectId id) {
		return complaintService.getById(id);
	}

	@DeleteMapping("{userName}/{id}")
	public ResponseEntity<?> complaintDelete(@PathVariable ObjectId id, @PathVariable String userName) {
		User user = userService.findUserName(userName).orElse(null);
		if (user != null) {
			complaintService.deleteComplaint(id, user);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}

	}

	@PutMapping("{userName}/{id}")
	public ResponseEntity<?> complaintUpdate(@PathVariable ObjectId id, @PathVariable String userName,
			@RequestBody Complaint complaint) {
		User user = userService.findUserName(userName).orElse(null);
		if (user != null) {
			return complaintService.updateComplaint(id, complaint, user);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
	}

}
