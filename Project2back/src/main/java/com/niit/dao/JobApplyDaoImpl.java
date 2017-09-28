package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.JobApply;

@Repository
@Transactional
public class JobApplyDaoImpl implements JobApplyDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void applyJob(JobApply jobapply) {
		Session session = sessionFactory.getCurrentSession();
		session.save(jobapply);
		
	}

	@Override
	public JobApply getappliedjobs(String userapp,int jid) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from JobApply where userapply = ? and jobid=?");
		query.setString(0, userapp);
		query.setInteger(1, jid);
		return (JobApply) query.uniqueResult();
	}

	@Override
	public List<JobApply> getApplicantsCount(int jid) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select userapply from JobApply where jobid=?");
		query.setInteger(0, jid);
		
		return query.list();
	}
}
