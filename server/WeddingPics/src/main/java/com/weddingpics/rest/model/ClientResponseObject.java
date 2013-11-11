package com.weddingpics.rest.model;

import java.io.Serializable;
import java.util.List;

public class ClientResponseObject implements Serializable {

	private static final long serialVersionUID = 9147035871587142715L;
	
	private Album album;
	private User user;
	private List<Picture> pictures;
	private Boolean isSuccess;
	private String errorMessage;
	
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Picture> getPictures() {
		return pictures;
	}
	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
	public Boolean getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	
}
