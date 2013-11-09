package com.weddingpics.rest.service.dao;

import com.weddingpics.rest.entity.User;


public interface UserServiceDAO {
	
	public void addUser(User user);
	public User findUserByEmailId(String emailId);
	public User findUserById(Long id);
	public User findUser(String emailId, String password);
	public void deleteUsers(String[] idList);
	public void UpdateUser(User user);
}
