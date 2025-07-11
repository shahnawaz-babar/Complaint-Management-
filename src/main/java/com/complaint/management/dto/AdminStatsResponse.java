package com.complaint.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminStatsResponse {

	private long totalComplaint;
	private long resolved;
	private long pending;
	private long inProgress;
	private long totalUser;
	
	
}
