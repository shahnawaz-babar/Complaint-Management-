package com.complaint.management.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.complaint.management.entity.Complaint;
import com.complaint.management.entity.User;
import com.complaint.management.repository.ComplaintRepo;
import com.complaint.management.repository.UserRepo;

@Component
public class ComplaintService {

	@Autowired
	private ComplaintRepo complaintRepo;
	@Autowired
	private UserService userService;

	public ResponseEntity<?> createComplaint(Complaint complain, User user) {
		complain.setCreateAt(LocalDateTime.now());
		complain.setUpdatedAt(LocalDateTime.now());
		complain.setStatus("Pending");
		complaintRepo.save(complain);
		user.getComplaints().add(complain);
		userService.createUser(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	public ResponseEntity<?> getALLComplain() {
		List<Complaint> list = complaintRepo.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	public ResponseEntity<?> deleteComplaint(ObjectId id, User user) {
		boolean complaint = user.getComplaints().removeIf(x -> x.getMyId().equals(id));
		if (complaint) {
			complaintRepo.deleteById(id);
			userService.createUser(user);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<?> updateComplaint(ObjectId id, Complaint newcomplaint, User user) {
		Complaint oldcomplaint = user.getComplaints().stream().filter(x -> x.getMyId().equals(id)).findFirst()
				.orElse(null);
		if (oldcomplaint != null) {
			oldcomplaint.setTitle(
					newcomplaint.getTitle() != null && !newcomplaint.getTitle().isEmpty() ? newcomplaint.getTitle()
							: oldcomplaint.getTitle());
			oldcomplaint
					.setDescription(newcomplaint.getDescription() != null && !newcomplaint.getDescription().isEmpty()
							? newcomplaint.getDescription()
							: oldcomplaint.getDescription());
			oldcomplaint.setUpdatedAt(LocalDateTime.now());

			oldcomplaint.setUpdatedAt(LocalDateTime.now());
			complaintRepo.save(oldcomplaint);
			userService.createUser(user);
			return new ResponseEntity<>(oldcomplaint, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<?> getById(ObjectId id) {
		Complaint complaint = complaintRepo.findById(id).orElse(null);
		if (complaint != null) {
			return new ResponseEntity<>(complaint, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<?> getComplaintsByStatus(String status) {
		List<Complaint> list = complaintRepo.findByStatusIgnoreCase(status);
		if (list != null) {
			return new ResponseEntity<>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	public ResponseEntity<?> recentComplaints() {
		List<Complaint> list = complaintRepo.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
