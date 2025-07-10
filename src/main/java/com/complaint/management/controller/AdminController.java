package com.complaint.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.complaint.management.service.ComplaintService;

public class AdminController {
	
	@Autowired
	ComplaintService complaintService;

	@GetMapping("/status/{status}")
	public ResponseEntity<?> getComplaintByStatus(@PathVariable String status)
	{
		return complaintService.getComplaintsByStatus(status);
	}
	
	@GetMapping("/recent")
	public ResponseEntity<?> recentComplaint()
	{
		return complaintService.recentComplaints();
	}
	
	
}
