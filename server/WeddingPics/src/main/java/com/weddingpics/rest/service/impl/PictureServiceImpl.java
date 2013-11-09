package com.weddingpics.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weddingpics.rest.entity.Picture;
import com.weddingpics.rest.service.PictureService;
import com.weddingpics.rest.service.dao.PictureServiceDAO;

@Service(value= "pictureService")
@Transactional
public class PictureServiceImpl implements PictureService {
	
	@Autowired
	private PictureServiceDAO pictureServiceDAO;

	@Override
	public void addPicture(Picture picture) {
		// TODO Auto-generated method stub
		pictureServiceDAO.addPicture(picture);
		
	}

	@Override
	public Picture findPictureById(Long id) {
		// TODO Auto-generated method stub
		return pictureServiceDAO.findPictureById(id);
	}

	@Override
	public void deletePictures(String[] idList) {
		// TODO Auto-generated method stub
		pictureServiceDAO.deletePictures(idList);
	}

	@Override
	public void UpdatePicture(Picture picture) {
		// TODO Auto-generated method stub
		pictureServiceDAO.UpdatePicture(picture);
	}

}
