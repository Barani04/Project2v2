package com.niit.dao;

import java.util.List;

import com.niit.model.Job;
import com.niit.model.JobApply;

public interface JobDao {
	void saveJob(Job job);
	
	List<Job> getAllJobs();
	
	Job getJobById(int id);

}
