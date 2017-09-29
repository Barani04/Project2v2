package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.ForumPosts;

@Repository
@Transactional
public class ForumPostDaoImpl implements ForumPostDao {

	@Autowired
	private SessionFactory	sessionFactory;
	
	
	@Override
	public void saveForumPost(ForumPosts forumpost) {
		Session session = sessionFactory.getCurrentSession();
		session.save(forumpost);
		
	}
	
	@Override
	public List<ForumPosts> getAllForumPosts(int forumId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ForumPosts where forid = ?");
		query.setInteger(0, forumId);
		return query.list();
	}

	@Override
	public ForumPosts getForumPostById(int fpid) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ForumPosts where id=?");
		query.setInteger(0, fpid);
		return (ForumPosts) query.uniqueResult();
	}

	

}
