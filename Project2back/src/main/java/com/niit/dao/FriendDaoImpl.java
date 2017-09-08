package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Friend;
import com.niit.model.User;

@Repository
@Transactional
public class FriendDaoImpl implements FriendDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<User> getListOfSuggestedUsers(String username) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select * from users where username in(select username from users where username != ? minus (select fromId from Friend where toId= ? and status!= 'D' union select toId from Friend where fromId= ? and status!= 'D'))";
		
		SQLQuery query = session.createSQLQuery(queryString);
		query.setString(0, username);
		query.setString(1, username);
		query.setString(2, username);
		query.addEntity(User.class);
		List<User> suggestedUsers = query.list();
		return suggestedUsers;
	}

	@Override
	public void friendRequest(String username, String toId) {
		
		Session session=sessionFactory.getCurrentSession();
		Friend friend = new Friend();
		friend.setFromId(username);
		friend.setToId(toId);
		friend.setStatus('P');
		session.save(friend);
		
		
	}

	@Override
	public List<User> getAllPendingRequests(String username) {
		Session session=sessionFactory.getCurrentSession();
		SQLQuery query =session.createSQLQuery("select * from users where username in (select fromId from Friend where toId = ? and status='P')");
		query.setString(0, username);
		query.addEntity(User.class);
		List<User> pendingRequests = query.list();
		return pendingRequests;
	}

	@Override
	public Friend getFriend(String name) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Friend where fromId = ?");
		query.setString(0, name);
		Friend friend = (Friend) query.uniqueResult();
		return friend;
		
	}

	@Override
	public void updateFriend(Friend friend) {
		Session session=sessionFactory.getCurrentSession();
		session.update(friend);
		
	}

}
