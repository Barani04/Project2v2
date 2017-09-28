package com.niit.dao;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.ForumRequest;

@Repository
@Transactional
public class ForumJoinDaoImpl implements ForumJoinDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void joinForum(ForumRequest forreq) {
		Session session = sessionFactory.getCurrentSession();
		session.save(forreq);
		
	}

	@Override
	public ForumRequest isParticipantUser(int forid,String userapp) {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.createQuery("from ForumRequest where forumid=? and joinuser =?");
		query.setInteger(0, forid);
		query.setString(1,userapp);
		ForumRequest forreq = (ForumRequest) query.uniqueResult();
		return forreq;
	}


	
}
