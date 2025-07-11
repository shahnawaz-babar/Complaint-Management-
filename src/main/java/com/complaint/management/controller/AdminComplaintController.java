package com.complaint.management.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.complaint.management.dto.AdminStatsResponse;
import com.complaint.management.entity.Complaint;
import com.complaint.management.repository.ComplaintRepo;
import com.complaint.management.service.ComplaintService;

@RequestMapping("/admin-complaint")
@RestController
public class AdminComplaintController {

	@Autowired
	ComplaintService complaintService;
	@Autowired
	ComplaintRepo complaintRepo;
	
	
	// Admin can see comlaint according to status
	@GetMapping("/status/{status}")
	public ResponseEntity<?> getComplaintByStatus(@PathVariable String status)
	{
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		return complaintService.getComplaintsByStatus(status);
	}
	
	// Admin can see complaint according to recent
	@GetMapping("/recent")
	public ResponseEntity<?> recentComplaint()
	{
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		return complaintService.recentComplaints();
	}
	
	
	@PutMapping("change-status/{id}/{status}")
	public ResponseEntity<?> updateComplaint(@PathVariable ObjectId id,@PathVariable String status)
	{
		 Complaint complaint=complaintRepo.findById(id).orElse(null);
		 complaint.setStatus(status);
		 complaint.setUpdatedAt(LocalDateTime.now());		
		 complaintRepo.save(complaint);
		 return new ResponseEntity<>(complaint,HttpStatus.OK);
	}
	
	  @GetMapping("/stats")
	    public ResponseEntity<AdminStatsResponse > getStats() {
	        AdminStatsResponse stats = complaintService.getStats();
	        return new ResponseEntity<>(stats, HttpStatus.OK);
	    }
	
	
}
