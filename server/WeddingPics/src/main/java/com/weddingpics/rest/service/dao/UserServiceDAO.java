package com.weddingpics.rest.service.dao;

import java.util.List;

import com.weddingpics.rest.entity.Album;
import com.weddingpics.rest.entity.User;
import com.weddingpics.rest.entity.UserAlbum;


public interface UserServiceDAO {
	
	public void addUser(User user);
	public User findUserByEmailId(String emailId);
	public User findUserById(Long id);
	public User findUser(String emailId, String password);
	public void deleteUsers(String[] idList);
	public void UpdateUser(User user);
	public User findUserByToken(String token);
	public UserAlbum getUserAlbum(Long UserId, Long AlbumId);
	public void addUserAlbum(UserAlbum userAlbum);
	public void UpdateUserAlbum(UserAlbum userAlbum);
	public List<Album> getUserAlbums(String token);
}
