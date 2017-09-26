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

import com.niit.dao.ForumDao;
import com.niit.dao.UserDao;
import com.niit.model.Forum;
import com.niit.model.User;
import com.niit.service.Error;

@Controller
public class ForumController {

	@Autowired
	ForumDao forumdao; 
	
	@Autowired
	private UserDao userdao;
	
	@RequestMapping(value="/saveforum",method=RequestMethod.POST)
	public ResponseEntity<?> createForum(@RequestBody Forum forum,HttpSession session) {
	
		if(session.getAttribute("username")==null){		
			Error error = new Error(5, "Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		String username = (String) session.getAttribute("username");
		User user = userdao.getUserByUsername(username);
		forum.setCreatedOn(new Date());
		forum.setCreatedBy(user);
		forum.setStatus(true);
		try{
			forumdao.saveForum(forum);
			return new ResponseEntity<Forum>(forum,HttpStatus.OK);
		}catch(Exception e){
			Error error = new Error(7,"Unable to Create Forum");
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/getallforum",method=RequestMethod.GET)
	public ResponseEntity<?> getAllForum(HttpSession session){
		if(session.getAttribute("username")==null){		
			Error error = new Error(5, "Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<Forum> forums = forumdao.getAllForum();
		return new ResponseEntity<List<Forum>>(forums, HttpStatus.OK);

		
	}
	
	@RequestMapping(value="/getforumbyid/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getForumById(@PathVariable("id") int forumId,HttpSession session){
		if(session.getAttribute("username")==null){		
			Error error = new Error(5, "Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		Forum forumdetail = forumdao.getForumById(forumId);
		return new ResponseEntity<Forum>(forumdetail,HttpStatus.OK);
	}
	
	
	
}
