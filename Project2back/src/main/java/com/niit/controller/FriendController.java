package com.niit.controller;

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

import com.niit.dao.FriendDao;
import com.niit.model.Friend;
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
	
	@RequestMapping(value="/friendrequest/{toId}",method=RequestMethod.POST)
	public ResponseEntity<?> sendFriendRequest(@PathVariable String toId,HttpSession session){
		if(session.getAttribute("username")==null){		
			Error error = new Error(5, "Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		String username = (String) session.getAttribute("username");
		frienddao.friendRequest(username,toId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/getAllPendingRequests",method=RequestMethod.GET)
	public ResponseEntity<?> getAllPendingRequests(HttpSession session) {
		if(session.getAttribute("username")==null){		
			Error error = new Error(5, "Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		String username = (String) session.getAttribute("username");
		List<User> pendingRequests = frienddao.getAllPendingRequests(username);
		return new ResponseEntity<List<User>>(pendingRequests,HttpStatus.OK);
	}
	
	@RequestMapping(value="/acceptpendingrequest/{name}",method=RequestMethod.PUT)
	public ResponseEntity<?> updatePendingRequest(@PathVariable String name,HttpSession session) {
		System.out.println("hhdhahnnadnjcnjj"+".................................."+name+"...........................hdncjnd");
		if(session.getAttribute("username")==null){		
			Error error = new Error(5, "Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		String username = (String) session.getAttribute("username");
		Friend friend = frienddao.getFriend(name,username);
		System.out.println(friend);
		friend.setStatus('A');
		frienddao.updateFriend(friend);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/denypendingrequest/{name}",method=RequestMethod.PUT)
	public ResponseEntity<?> denyPendingRequest(@PathVariable String name,HttpSession session) {
		System.out.println("hhdhahnnadnjcnjj"+".................................."+name+"...........................hdncjnd");
		if(session.getAttribute("username")==null){		
			Error error = new Error(5, "Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		String username = (String) session.getAttribute("username");
		Friend friend = frienddao.getFriend(name,username);
		friend.setStatus('D');
		frienddao.updateFriend(friend);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/getfriendlist",method=RequestMethod.GET)
	public ResponseEntity<?> getFriendList(HttpSession session){
		if(session.getAttribute("username")==null){		
			Error error = new Error(5, "Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		String username =(String) session.getAttribute("username");
		List<Friend> friends = frienddao.friendList(username);
		return new ResponseEntity<List<Friend>>(friends,HttpStatus.OK);
	}
	
}
