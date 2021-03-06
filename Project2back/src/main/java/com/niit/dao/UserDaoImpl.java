package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{
	@Autowired
	private SessionFactory sessionFactory;
	public void registerUser(User user) {
		Session session=sessionFactory.getCurrentSession();
		session.save(user);	
	}
	public User validateUsername(String username) {
		Session session =sessionFactory.getCurrentSession();
		User user= (User) session.get(User.class, username);
		return user;
	}
	public User validateEmail(String email) {
		Session session =sessionFactory.getCurrentSession();
		Query query =session.createQuery("from User where email=?");
		query.setString(0, email);
		User user = (User) query.uniqueResult();
		return user;
	}
	public User login(User user) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where username=? and password=?");
		query.setString(0, user.getUsername());
		query.setString(1, user.getPassword());
		return (User)query.uniqueResult();
	}
	public void update(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.update(user);
		
	}
	public User getUserByUsername(String username) {
		System.out.println(username);
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, username);
		return user;
	}
	@SuppressWarnings("unchecked")
	public List<User> getUsers(int activated) {
		Session session =sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where activated="+activated);
		return query.list();
	}
	@Override
	public boolean updateUserProfile(String fileName, String username) {
		
		String statement = "update User set profilepic = ? where username = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(statement);
		query.setString(0, fileName);
		query.setString(1, username);
		try {
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
		
	}

}
