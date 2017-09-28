package com.niit.dao;

import com.niit.model.ForumRequest;

public interface ForumJoinDao {

	void joinForum(ForumRequest forreq);

	ForumRequest isParticipantUser(int forid, String userapp);

}
