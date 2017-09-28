package com.niit.dao;

import java.util.List;

import com.niit.model.Job;
import com.niit.model.JobApply;

public interface JobApplyDao {

	void applyJob(JobApply jobapply);

	JobApply getappliedjobs(String userapp, int jid);

	List<JobApply> getApplicantsCount(int jid);

	List<Job> getUserAppliedJobs(String userapp);

}
