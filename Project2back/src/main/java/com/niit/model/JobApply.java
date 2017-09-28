package com.niit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class JobApply {
	
	@Id
	@Column(name="applyId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private int jobid;
	
	
	private String userapply;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getUserapply() {
		return userapply;
	}


	public void setUserapply(String userapply) {
		this.userapply = userapply;
	}


	public int getJobid() {
		return jobid;
	}


	public void setJobid(int jobid) {
		this.jobid = jobid;
	}

	
}
