package com.niit.dao;

import java.util.List;

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

	@Override
	public List<ForumRequest> getJoinRequests(int joinstatus) {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.createQuery("from ForumRequest where reqstatus = ?");
		query.setInteger(0, joinstatus);
		List<ForumRequest> forreq = query.list();
		return forreq;
	}
	
	@Override
	public void acceptJoinReq(ForumRequest forreq) {
		Session session = sessionFactory.getCurrentSession();
		session.update(forreq);
	}

	@Override
	public ForumRequest getRequestById(int requestid) {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.createQuery("from ForumRequest where reqid = ?");
		query.setInteger(0,requestid);
		ForumRequest forreq = (ForumRequest) query.uniqueResult();
		return forreq;
	}


	
}
