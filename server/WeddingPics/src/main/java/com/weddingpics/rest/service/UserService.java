package com.weddingpics.rest.service;

import org.springframework.stereotype.Service;

import com.weddingpics.rest.entity.User;

@Service
public interface UserService {
	
	public void addUser(User user);
	public User findUserByEmailId(String emailId);
	public User findUserById(Long id);
	public User findUser(String emailId, String password);
	public void deleteUsers(String[] idList);
	public void UpdateUser(User user);
	
}
