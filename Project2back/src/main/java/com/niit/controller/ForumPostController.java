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

import com.niit.dao.ForumPostDao;
import com.niit.dao.UserDao;
import com.niit.model.Forum;
import com.niit.model.ForumPosts;
import com.niit.model.User;
import com.niit.service.Error;

@Controller
public class ForumPostController {

	@Autowired
	ForumPostDao forumpostdao;
	
	@Autowired
	UserDao userdao;
	
	@RequestMapping(value="/addforumpost",method=RequestMethod.POST)
	public ResponseEntity<?> createForum(@RequestBody ForumPosts forumpost,HttpSession session) {
	
		if(session.getAttribute("username")==null){		
			Error error = new Error(5, "Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		String username = (String) session.getAttribute("username");
		User user = userdao.getUserByUsername(username);
		forumpost.setPostedBy(user);
		forumpost.setPostDate(new Date());
		
		try{
			forumpostdao.saveForumPost(forumpost);
			return new ResponseEntity<ForumPosts>(forumpost,HttpStatus.OK);
		}catch(Exception e){
			Error error = new Error(7,"Unable to Create Forum");
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/viewforumposts/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getAllForumPosts(@PathVariable("id") int forumId,HttpSession session) {
		if(session.getAttribute("username")==null){		
			Error error = new Error(5, "Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<ForumPosts> forumposts = forumpostdao.getAllForumPosts(forumId);
		
		return new ResponseEntity<List<ForumPosts>>(forumposts,HttpStatus.OK);
	}
	
	@RequestMapping(value="/viewforumpost/{fpid}",method=RequestMethod.GET)
	public ResponseEntity<?> getForumPost(@PathVariable("fpid") int fpid,HttpSession session) {
		if(session.getAttribute("username")==null){		
			Error error = new Error(5, "Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		ForumPosts forum = forumpostdao.getForumPostById(fpid);
		return new ResponseEntity<ForumPosts>(forum, HttpStatus.OK);
	}
}
