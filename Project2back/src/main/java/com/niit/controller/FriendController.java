package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.dao.FriendDao;
import com.niit.model.User;
import com.niit.service.Error;

@Controller
public class FriendController {

	@Autowired
	private FriendDao frienddao;
	
	@RequestMapping(value="/getsuggestedusers",method=RequestMethod.GET)
	public ResponseEntity<?> getListOfSuggestedUsers(HttpSession session) {
		if(session.getAttribute("username")==null){		
			Error error = new Error(5, "Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		String username = (String) session.getAttribute("username");
		List<User> suggestedUsers = frienddao.getListOfSuggestedUsers(username);
		return new ResponseEntity<List<User>>(suggestedUsers,HttpStatus.OK);
		
	}
	
}
