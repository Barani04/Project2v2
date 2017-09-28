package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Forum;

@Repository
@Transactional
public class ForumDaoImpl implements ForumDao {

	@Autowired
	private SessionFactory	sessionFactory;
	
	public void saveForum(Forum forum) {
		Session session = sessionFactory.getCurrentSession();
		session.save(forum);
		
	}

	@Override
	public List<Forum> getAllForum(int approved) {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.createQuery("from Forum where status="+approved);
		return query.list();
	}

	@Override
	public Forum getForumById(int forumId) {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.createQuery("from Forum where id=?");
		query.setInteger(0, forumId);
		Forum forum = (Forum) query.uniqueResult();
		return forum;
	}

	@Override
	public void updateForum(Forum forum) {
		Session session = sessionFactory.getCurrentSession();
		session.update(forum);
		
	}
}
