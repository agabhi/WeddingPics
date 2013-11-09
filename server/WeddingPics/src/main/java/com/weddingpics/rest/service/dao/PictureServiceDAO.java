package com.weddingpics.rest.service.dao;

import com.weddingpics.rest.entity.Picture;

public interface PictureServiceDAO {
	
	public void addPicture(Picture picture);
	public Picture findPictureById(Long id);
	public void deletePictures(String[] idList);
	public void UpdatePicture(Picture picture);

}
