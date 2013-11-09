package com.weddingpics.rest.model;

import java.io.Serializable;
import java.util.Date;


public class Picture implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1519128441383896248L;

	
	
	protected Long pictureId;
	private User user;
	private Album album;
	private String pictureTitle;
	private String url;
	private Date pictureDate;
	private Integer imageType;
	
	public Long getPictureId() {
		return pictureId;
	}
	public void setPictureId(Long pictureId) {
		this.pictureId = pictureId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	public String getPictureTitle() {
		return pictureTitle;
	}
	public void setPictureTitle(String pictureTitle) {
		this.pictureTitle = pictureTitle;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getPictureDate() {
		return pictureDate;
	}
	public void setPictureDate(Date pictureDate) {
		this.pictureDate = pictureDate;
	}
	public Integer getImageType() {
		return imageType;
	}
	public void setImageType(Integer imageType) {
		this.imageType = imageType;
	}
	
	

}
