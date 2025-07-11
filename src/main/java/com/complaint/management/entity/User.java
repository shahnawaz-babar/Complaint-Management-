package com.complaint.management.entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="user")
public class User {

	@Id
	private ObjectId id;
	@Indexed(unique = true)
	private String userName;
	private String password;
	private List<String> roles;
	@DBRef
	List<Complaint> complaints=new ArrayList<Complaint>();
	
}
