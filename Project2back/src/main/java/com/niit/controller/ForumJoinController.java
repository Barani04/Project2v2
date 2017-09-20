package com.niit.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.dao.ForumDao;
import com.niit.dao.ForumRequestDao;
import com.niit.dao.UserDao;
import com.niit.model.Forum;
import com.niit.model.ForumRequest;
import com.niit.model.User;
import com.niit.service.Error;

@Controller
public class ForumJoinController {

	@Autowired
	ForumRequestDao forumreqdao;
	
	@Autowired
	ForumDao forumdao;
	
	@Autowired
	UserDao userdao;
	
	
	@RequestMapping(value="/joinforum/{userreq}")
	public ResponseEntity<?> joinForum(@PathVariable("userreq") String username,@RequestParam("forumId") int forumId,HttpSession session) {
		if(session.getAttribute("username")==null){		
			Error error = new Error(5, "Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		ForumRequest forumreq = null;
		User user = userdao.getUserByUsername(username);
		Forum forum =forumdao.getForumById(forumId);
		forumreq.setForum(forum);
		forumreq.setUsername(username);
		
		if(user.getRole() == "ADMIN"){
			forumreq.setStatus("APPROVED");
		}else{
			forumreq.setStatus("PENDING");
		}
		forumreqdao.addForumRequests(forumreq);
		return new ResponseEntity<ForumRequest>(forumreq,HttpStatus.OK);	
	}
	
	

}
