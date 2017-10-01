package com.niit.dao;

import java.util.List;

import com.niit.model.ForumRequest;

public interface ForumJoinDao {

	void joinForum(ForumRequest forreq);

	ForumRequest isParticipantUser(int forid, String userapp);

	List<ForumRequest> getJoinRequests(int joinstatus);

	void acceptJoinReq(ForumRequest forreq);

	ForumRequest getRequestById(int requestid);
}
