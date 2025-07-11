package com.complaint.management.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.complaint.management.entity.Complaint;

public interface ComplaintRepo extends MongoRepository<Complaint,ObjectId>{

	List<Complaint> findByStatusIgnoreCase(String status);
	long countByStatusIgnoreCase(String status);
	
}
