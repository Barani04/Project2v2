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
	
	private String forumTitle;
	
	private boolean reqstatus;

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

	public boolean isReqstatus() {
		return reqstatus;
	}

	public void setReqstatus(boolean reqstatus) {
		this.reqstatus = reqstatus;
	}

	public String getForumTitle() {
		return forumTitle;
	}

	public void setForumTitle(String forumTitle) {
		this.forumTitle = forumTitle;
	}
	
	
}
