package com.complaint.management.entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="complaint")
public class Complaint {

	@Id
	private ObjectId myId;
	private String title;
	private String description;
	private LocalDateTime createAt;
	private LocalDateTime updatedAt;
	private String status;
	
	
	
}
