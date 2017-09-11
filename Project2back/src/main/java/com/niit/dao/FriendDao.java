package com.niit.dao;

import java.util.List;

import com.niit.model.Friend;
import com.niit.model.User;

public interface FriendDao {

	List<User> getListOfSuggestedUsers(String username);

	void friendRequest(String username, String toId);

	List<User> getAllPendingRequests(String username);

	Friend getFriend(String name,String username);

	void updateFriend(Friend friend);
	
	List<Friend> friendList(String username);
	
	
}
