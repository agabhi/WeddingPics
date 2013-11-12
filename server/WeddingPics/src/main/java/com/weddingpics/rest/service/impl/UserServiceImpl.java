package com.weddingpics.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weddingpics.rest.entity.Album;
import com.weddingpics.rest.entity.User;
import com.weddingpics.rest.entity.UserAlbum;
import com.weddingpics.rest.service.UserService;
import com.weddingpics.rest.service.dao.UserServiceDAO;
@Service(value= "userService")
@Transactional
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserServiceDAO userServiceDao;

	@Override
	public void addUser(User user) {
		userServiceDao.addUser(user);
		
	}

	@Override
	public User findUserByEmailId(String emailId) {
		// TODO Auto-generated method stub
		return userServiceDao.findUserByEmailId(emailId);
	}

	@Override
	public User findUser(String emailId, String password) {
		// TODO Auto-generated method stub
		return userServiceDao.findUser(emailId, password);
	}

	@Override
	public void deleteUsers(String[] idList) {
		 userServiceDao.deleteUsers(idList);
		
	}

	@Override
	public void UpdateUser(User user) {
		userServiceDao.UpdateUser(user);
		
	}

	@Override
	public User findUserById(Long id) {
		// TODO Auto-generated method stub
		return userServiceDao.findUserById(id);
	}

	@Override
	public User findUserByToken(String token) {
		// TODO Auto-generated method stub
		return userServiceDao.findUserByToken(token);
	}

	@Override
	public UserAlbum getUserAlbum(Long userId, Long albumId) {
		// TODO Auto-generated method stub
		return userServiceDao.getUserAlbum(userId, albumId);
	}

	@Override
	public void addUserAlbum(UserAlbum userAlbum) {
		// TODO Auto-generated method stub
		userServiceDao.addUserAlbum(userAlbum);
	}

	@Override
	public void UpdateUserAlbum(UserAlbum userAlbum) {
		// TODO Auto-generated method stub
		userServiceDao.UpdateUserAlbum(userAlbum);
	}

	@Override
	public List<Album> getUserAlbums(String token) {
		// TODO Auto-generated method stub
		return userServiceDao.getUserAlbums(token);
	}
	
	

	
}
