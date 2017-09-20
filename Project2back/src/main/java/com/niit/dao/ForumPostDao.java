package com.niit.dao;

import java.util.List;

import com.niit.model.ForumPosts;

public interface ForumPostDao {

	List<ForumPosts> getAllForumPosts(int forumId);

	ForumPosts getForumPostById(int fpid);

	void saveForumPost(ForumPosts forumpost);

}
