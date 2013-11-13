package com.weddingpics.rest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.weddingpics.rest.entity.Album;
import com.weddingpics.rest.entity.User;
import com.weddingpics.rest.entity.UserAlbum;

@Service
public interface UserService {
	
	public void addUser(User user);
	public User findUserByEmailId(String emailId);
	public User findUserById(Long id);
	public User findUser(String emailId, String password);
	public void deleteUsers(String[] idList);
	public void UpdateUser(User user);
	public User findUserByToken(String token);
	public UserAlbum getUserAlbum(Long userId, Long albumId);
	public void addUserAlbum(UserAlbum userAlbum);
	public void UpdateUserAlbum(UserAlbum userAlbum);
	public List<Album> getUserAlbums(String token);
	
}
