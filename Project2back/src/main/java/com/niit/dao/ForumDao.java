package com.niit.dao;

import java.util.List;

import com.niit.model.Forum;

public interface ForumDao {
	
	void saveForum(Forum forum);

	List<Forum> getAllForum();
}
