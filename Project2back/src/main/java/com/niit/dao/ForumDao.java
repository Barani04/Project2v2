package com.niit.dao;

import java.util.List;

import com.niit.model.Forum;

public interface ForumDao {
	
	void saveForum(Forum forum);


	Forum getForumById(int forumId);

	List<Forum> getAllForum(int approved);


	void updateForum(Forum forum);
}
