package com.weddingpics.rest.service.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.weddingpics.rest.entity.Album;
import com.weddingpics.rest.entity.User;
import com.weddingpics.rest.entity.UserAlbum;
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

	@Override
	public User findUserByToken(String token) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		try
		{
			@SuppressWarnings("unchecked")
			List<User> usersList = session.createCriteria(User.class)
					.add(Restrictions.eq("token",token ))
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
	public UserAlbum getUserAlbum(Long userId, Long albumId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		try
		{
			@SuppressWarnings("unchecked")
			List<UserAlbum> userAlbums = session.createCriteria(UserAlbum.class)
					.add(Restrictions.eq("user.userId",userId ))
					.add(Restrictions.eq("album.albumId",albumId ))
					.list();
			if (CollectionUtils.isNotEmpty(userAlbums)) {
				UserAlbum userAlbum = userAlbums.get(0);	
				return userAlbum;
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
	public void addUserAlbum(UserAlbum userAlbum) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		try
		{
			session.save(userAlbum);
		}
		finally
		{
			//session.close();
		}
		
	}

	@Override
	public void UpdateUserAlbum(UserAlbum userAlbum) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		try
		{
			session.saveOrUpdate(userAlbum);
		}
		finally
		{
			//session.close();
		}
	}

	@Override
	public List<Album> getUserAlbums(String token) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select ua.album from UserAlbum as ua where ua.user.token = :token").setParameter("token", token);
		@SuppressWarnings("unchecked")
		List<Album> list = (List<Album>)query.list();
		return CollectionUtils.isNotEmpty(list) ? list : null;
	}
	
	
}
