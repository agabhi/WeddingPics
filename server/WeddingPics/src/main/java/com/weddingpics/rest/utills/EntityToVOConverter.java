package com.weddingpics.rest.utills;

import com.weddingpics.rest.model.Album;
import com.weddingpics.rest.model.Picture;
import com.weddingpics.rest.model.User;



public class EntityToVOConverter {


	public static User convertToVO(com.weddingpics.rest.entity.User user) {

		User userModel = null;
		if (user != null) {
			userModel = new User();
			userModel.setEmailId(user.getEmailId());
			userModel.setFullName(user.getFullName());
			userModel.setModifyDttm(user.getModifyDttm());
			userModel.setPassword(user.getPassword());
			userModel.setToken(user.getToken());
			userModel.setUserId(user.getUserId());
		}
		return userModel;
	}
	
	public static Album convertToVO(com.weddingpics.rest.entity.Album album) {

		Album albumModel = null;
		if (album != null) {
			albumModel = new Album();
			albumModel.setAlbumId(album.getAlbumId());
			albumModel.setCoverImage(album.getCoverImage());
			albumModel.setDescription(album.getDescription());
			albumModel.setFirstUser(album.getFirstUser());
			albumModel.setFirstUserType(album.getFirstUserType());
			albumModel.setModifyDttm(album.getModifyDttm());
			albumModel.setSecondUser(album.getSecondUser());
			albumModel.setWeddingdate(album.getWeddingdate());
			albumModel.setWeddingId(album.getWeddingId());
		}
		return albumModel;
	}
	
	public static Picture convertToVO(com.weddingpics.rest.entity.Picture picture) {

		Picture pictureModel = null;
		if (picture != null) {
			pictureModel = new Picture();
			pictureModel.setImageType(picture.getImageType());
			pictureModel.setPictureDate(picture.getPictureDate());
			pictureModel.setPictureId(picture.getPictureId());
			pictureModel.setPictureTitle(picture.getPictureTitle());
			pictureModel.setUrl(picture.getUrl());
		}
		return pictureModel;
	}

	}
