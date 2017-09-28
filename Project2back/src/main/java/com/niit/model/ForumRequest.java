package com.niit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ForumRequest {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int reqid;
	
	private String joinuser;
	
	private int forumid;

	public int getReqid() {
		return reqid;
	}

	public void setReqid(int reqid) {
		this.reqid = reqid;
	}

	public String getJoinuser() {
		return joinuser;
	}

	public void setJoinuser(String joinuser) {
		this.joinuser = joinuser;
	}

	public int getForumid() {
		return forumid;
	}

	public void setForumid(int forumid) {
		this.forumid = forumid;
	}
	
	
}
