package com.weddingpics.rest.service.dao;

import com.weddingpics.rest.entity.Album;

public interface AlbumServiceDAO {

	public void addAlbum(Album album);
	public Album findAlbumById(Long albumId);
	public Album findAlbumByUserId(Long userId);
	public void deleteAlbums(String[] idList);
	public void UpdateAlbum(Album album);
	public Album findAlbumByWeddingId(String weddingId);
}
