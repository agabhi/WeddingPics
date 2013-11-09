package com.weddingpics.rest.service;

import org.springframework.stereotype.Service;

import com.weddingpics.rest.entity.Picture;

@Service
public interface PictureService {
	
	public void addPicture(Picture picture);
	public Picture findPictureById(Long id);
	public void deletePictures(String[] idList);
	public void UpdatePicture(Picture picture);

}
