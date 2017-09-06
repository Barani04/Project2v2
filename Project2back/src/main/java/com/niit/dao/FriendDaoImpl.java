package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.User;

@Repository
@Transactional
public class FriendDaoImpl implements FriendDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<User> getListOfSuggestedUsers(String username) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select * from users where username in(select username from users where username != ? minus (select fromId from Friend where toId != ? and status != 'D' union select toId from Friend where fromId != ? and status != 'D'))";
		
		SQLQuery query = session.createSQLQuery(queryString);
		query.setString(0, username);
		query.setString(1, username);
		query.setString(2, username);
		query.addEntity(User.class);
		List<User> suggestedUsers = query.list();
		return suggestedUsers;
	}

}
