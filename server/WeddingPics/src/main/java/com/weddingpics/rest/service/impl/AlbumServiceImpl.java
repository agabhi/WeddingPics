package com.weddingpics.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weddingpics.rest.entity.Album;
import com.weddingpics.rest.entity.User;
import com.weddingpics.rest.service.AlbumService;
import com.weddingpics.rest.service.dao.AlbumServiceDAO;

@Service(value= "albumService")
@Transactional
public class AlbumServiceImpl implements AlbumService  {
	
	@Autowired
	private AlbumServiceDAO albumServiceDAO;

	@Override
	public void addAlbum(Album album) {
		albumServiceDAO.addAlbum(album);
		
	}

	@Override
	public Album findAlbumById(Long albumId) {
		// TODO Auto-generated method stub
		return albumServiceDAO.findAlbumById(albumId);
	}

	@Override
	public Album findAlbumByUserId(Long userId) {
		// TODO Auto-generated method stub
		return albumServiceDAO.findAlbumByUserId(userId);
	}

	@Override
	public void deleteAlbums(String[] idList) {
		// TODO Auto-generated method stub
		albumServiceDAO.deleteAlbums(idList);
	}

	@Override
	public void UpdateAlbum(Album album) {
		// TODO Auto-generated method stub
		albumServiceDAO.UpdateAlbum(album);
	}

	@Override
	public Album findAlbumByWeddingId(String weddingId) {
		// TODO Auto-generated method stub
		return albumServiceDAO.findAlbumByWeddingId(weddingId);
	}

}
