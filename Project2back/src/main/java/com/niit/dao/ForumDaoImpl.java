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
		session.saveOrUpdate(forum);
		
	}

	@Override
	public List<Forum> getAllForum() {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.createQuery("from Forum");
		return query.list();
	}
	

}
