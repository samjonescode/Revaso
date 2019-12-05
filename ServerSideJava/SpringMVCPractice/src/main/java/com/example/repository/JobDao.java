package com.example.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.model.Job;

@Repository("jobRepo")
@Transactional
public class JobDao {
	
	@Autowired
	SessionFactory sesFact;
	
	public void insert(Job job) {
		sesFact.getCurrentSession().save(job);
	}
	
	public List<Job> selectAll() {
		return sesFact.getCurrentSession().createQuery("From Job", Job.class).list();
	}

	public Job selectById(int id) {
		return sesFact.getCurrentSession().get(Job.class, id);
	}
	
}
