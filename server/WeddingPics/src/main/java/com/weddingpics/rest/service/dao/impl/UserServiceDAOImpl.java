package com.weddingpics.rest.service.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.weddingpics.rest.entity.User;
import com.weddingpics.rest.service.dao.UserServiceDAO;

@Repository(value= "userServiceDAO")
@Transactional
public class UserServiceDAOImpl implements UserServiceDAO 
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		try
		{
			session.save(user);
		}
		finally
		{
			//session.close();
		}
	}

	@Override
	public User findUserByEmailId(String emailId) {
		Session session = sessionFactory.getCurrentSession();
		try
		{
			@SuppressWarnings("unchecked")
			List<User> usersList = session.createCriteria(User.class)
					.add(Restrictions.eq("emailId",emailId ))
					.list();
			if (CollectionUtils.isNotEmpty(usersList)) {
				User user = usersList.get(0);	
				return user;
			} else {
				return null;
			}
		}
		finally
		{
			//session.close();
		}
	}

	@Override
	public User findUser(String emailId, String password) {
		Session session = sessionFactory.getCurrentSession();
		try
		{
			@SuppressWarnings("unchecked")
			List<User> usersList = session.createCriteria(User.class)
					.add(Restrictions.eq("emailId",emailId ))
					.add(Restrictions.eq("password",password ))
					.list();
			if (CollectionUtils.isNotEmpty(usersList)) {
				User user = usersList.get(0);	
				return user;
			} else {
				return null;
			}
		}
		finally
		{
			//session.close();
		}
	}

	@Override
	public void deleteUsers(String[] idList) {
		Session session = sessionFactory.getCurrentSession();
		try
		{
			session.createSQLQuery("delete from User where userId in (:list)").setParameterList("list", idList).executeUpdate();
		}
		finally
		{
			//session.close();
		}
		
	}

	@Override
	public void UpdateUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		try
		{
			session.saveOrUpdate(user);
		}
		finally
		{
			//session.close();
		}
		
	}

	@Override
	public User findUserById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		try
		{
			@SuppressWarnings("unchecked")
			List<User> usersList = session.createCriteria(User.class)
					.add(Restrictions.eq("userId",id ))
					.list();
			if (CollectionUtils.isNotEmpty(usersList)) {
				User user = usersList.get(0);	
				return user;
			} else {
				return null;
			}
		}
		finally
		{
			//session.close();
		}
	}
	
	
}
