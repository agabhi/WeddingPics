package com.weddingpics.rest.service;

import org.springframework.stereotype.Service;

import com.weddingpics.rest.entity.Album;

@Service
public interface AlbumService {
	
	public void addAlbum(Album album);
	public Album findAlbumById(Long albumId);
	public Album findAlbumByUserId(Long userId);
	public void deleteAlbums(String[] idList);
	public void UpdateAlbum(Album album);
	public Album findAlbumByWeddingId(String weddingId);

}
