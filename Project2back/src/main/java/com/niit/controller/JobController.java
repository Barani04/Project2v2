package com.niit.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.dao.JobApplyDao;
import com.niit.dao.JobDao;
import com.niit.dao.UserDao;
import com.niit.model.Job;
import com.niit.model.JobApply;
import com.niit.model.User;
import com.niit.service.Error;

@Controller
public class JobController {
	
	@Autowired
	private UserDao userdao;
	
	@Autowired
	private JobDao jobdao;
	
	@Autowired
	private JobApplyDao jobappdao;

	
	@RequestMapping(value="/savejob",method=RequestMethod.POST)
	public ResponseEntity<?> saveJob(@RequestBody Job job,HttpSession session){		
		String username = (String) session.getAttribute("username");		
		if(session.getAttribute("username")==null){		
			Error error = new Error(5, "Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		User user = userdao.getUserByUsername(username); 
		if(user.getRole().equals("ADMIN")){
			try{
				
				job.setPostedOn(new Date());				
				jobdao.saveJob(job);		
				return new ResponseEntity<Job>(job,HttpStatus.OK);
			}catch (Exception e) {
				Error error = new Error(7,"Unable to Post Job");
				return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else{
			Error error = new Error(6, "Access Denied");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@RequestMapping(value="/getalljobs",method = RequestMethod.GET)
	public ResponseEntity<?> getAllJobs(HttpSession session){
		if(session.getAttribute("username")==null){
			Error error = new Error(5, "Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<Job> jobs = jobdao.getAllJobs();
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getjobbyid/{jid}",method = RequestMethod.GET)
	public ResponseEntity<?> getJobById(@PathVariable int jid,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error = new Error(5, "Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		Job job = jobdao.getJobById(jid);
		return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
	
	@RequestMapping(value="/applyjob/{jobid}",method=RequestMethod.POST)
	public ResponseEntity<?> applyJob(@PathVariable("jobid") int jid,HttpSession session) {
		if(session.getAttribute("username")==null){
			Error error = new Error(5, "Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		String userapp =  (String) session.getAttribute("username");
		System.out.println(userapp);
		try{
			JobApply jobapply = new JobApply();
			jobapply.setJobid(jid);
			jobapply.setUserapply(userapp);
			jobappdao.applyJob(jobapply);
			return new ResponseEntity<JobApply>(jobapply,HttpStatus.OK);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			Error error = new Error(10, "Value Not Acceptable");
			return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	@RequestMapping(value="/getappjobs/{jobid}",method = RequestMethod.GET)
	public ResponseEntity<?> getAppliedJobs(@PathVariable("jobid") int jid,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error = new Error(5, "Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		System.out.println(jid);
		String userapp =  (String) session.getAttribute("username");
		JobApply jobapplied = jobappdao.getappliedjobs(userapp,jid);
		return new ResponseEntity<JobApply>(jobapplied,HttpStatus.OK);
	}
	
	@RequestMapping(value="/applicantsnum/{jobid}",method=RequestMethod.GET)
	public ResponseEntity<?> noOfApplicants(@PathVariable("jobid") int jid,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error = new Error(5, "Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		System.out.println(jid);
		List<JobApply> applicants= jobappdao.getApplicantsCount(jid);
		int count = applicants.size();
		return new ResponseEntity<Integer>(count,HttpStatus.OK);
	}
	
	@RequestMapping(value="/appliedjobs/{userId}",method=RequestMethod.GET)
	public ResponseEntity<?> userAppliedJobs(@PathVariable("userId") String userapp,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error = new Error(5, "Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<Job> appliedjobs = jobappdao.getUserAppliedJobs(userapp); 
		return new ResponseEntity<List<Job>>(appliedjobs,HttpStatus.OK);
		
	}
	
	
}
