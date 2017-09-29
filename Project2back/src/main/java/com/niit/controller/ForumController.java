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
import com.niit.dao.ForumJoinDao;
import com.niit.dao.ForumPostDao;
import com.niit.dao.UserDao;
import com.niit.model.Blog;
import com.niit.model.Forum;
import com.niit.model.ForumPosts;
import com.niit.model.ForumRequest;
import com.niit.model.User;
import com.niit.service.EmailService;
import com.niit.service.Error;

@Controller
public class ForumController {

	@Autowired
	ForumDao forumdao; 
	
	@Autowired
	private UserDao userdao;
	
	@Autowired
	private ForumJoinDao forjoindao;
	
	@Autowired
	private ForumPostDao forpostdao;
	
	@Autowired
	private EmailService emailService;
	
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
		if(forum.getCreatedBy().getRole() =="ADMIN"){
			forum.setStatus(true);
		}
		try{
			forumdao.saveForum(forum);
			return new ResponseEntity<Forum>(forum,HttpStatus.OK);
		}catch(Exception e){
			Error error = new Error(7,"Unable to Create Forum");
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/getallforum/{status}",method=RequestMethod.GET)
	public ResponseEntity<?> getAllForum(@PathVariable int status,HttpSession session){
		if(session.getAttribute("username")==null){		
			Error error = new Error(5, "Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<Forum> forums = forumdao.getAllForum(status);
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
	
	@RequestMapping(value="/approveforum/{id}",method=RequestMethod.POST)
	public ResponseEntity<?> approveForum(@PathVariable("id") int fid,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error = new Error(5, "Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		Forum forum = forumdao.getForumById(fid);
		forum.setStatus(true);
		forumdao.updateForum(forum);
		emailService.approvedForumNotify(forum);
		return new ResponseEntity<Forum>(forum,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/joinforum/{forumid}",method=RequestMethod.POST)
	public ResponseEntity<?> joinForum(@PathVariable("forumid") int forid,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error = new Error(5, "Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}	
		String userapp =  (String) session.getAttribute("username");
		System.out.println(userapp);
		try{
			ForumRequest forreq = new ForumRequest();
			forreq.setJoinuser(userapp);
			forreq.setForumid(forid);
			forjoindao.joinForum(forreq);
			return new ResponseEntity<ForumRequest>(forreq,HttpStatus.OK);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			Error error = new Error(10, "Value Not Acceptable");
			return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@RequestMapping(value="/isparticipant/{forumid}",method=RequestMethod.GET)
	public ResponseEntity<?> isParticipant(@PathVariable("forumid") int forid,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error = new Error(5, "Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}	
		String userapp =  (String) session.getAttribute("username");
		ForumRequest forreq = forjoindao.isParticipantUser(forid,userapp);
		return new ResponseEntity<ForumRequest>(forreq,HttpStatus.OK);
	}
	
	@RequestMapping(value="/add/forumpost",method=RequestMethod.POST)
	public ResponseEntity<?> addForumPost(@RequestBody ForumPosts forumpost,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error = new Error(5, "Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		forumpost.setPostDate(new Date());
		try{
			forpostdao.saveForumPost(forumpost);
			return new ResponseEntity<ForumPosts>(forumpost,HttpStatus.OK);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			Error error = new Error(10, "Value Not Acceptable");
			return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@RequestMapping(value="/list/forumpost/{forumid}")
	public ResponseEntity<?> listForumPosts(@PathVariable("forumid") int forumid,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error = new Error(5, "Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<ForumPosts> forumposts = forpostdao.getAllForumPosts(forumid);
		return new ResponseEntity<List<ForumPosts>>(forumposts,HttpStatus.OK);
		
	}
		
}
