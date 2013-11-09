package com.weddingpics.rest.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class Album implements Serializable {

	private static final long serialVersionUID = 2960747720739566249L;
	
	protected Long albumId;
	private User user;
	private String weddingId;
	private String firstUser;
	private String secondUser;
	private Integer firstUserType;
	private Integer secondUserType;
	private Date weddingdate;
	private String description;
	private Date modifyDttm;
	private String coverImage;
	
	private List<Picture> pictures;
	
	public Long getAlbumId() {
		return albumId;
	}
	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getWeddingId() {
		return weddingId;
	}
	public void setWeddingId(String weddingId) {
		this.weddingId = weddingId;
	}
	public String getFirstUser() {
		return firstUser;
	}
	public void setFirstUser(String firstUser) {
		this.firstUser = firstUser;
	}
	public String getSecondUser() {
		return secondUser;
	}
	public void setSecondUser(String secondUser) {
		this.secondUser = secondUser;
	}
	public Integer getFirstUserType() {
		return firstUserType;
	}
	public void setFirstUserType(Integer firstUserType) {
		this.firstUserType = firstUserType;
	}
	public Integer getSecondUserType() {
		return secondUserType;
	}
	public void setSecondUserType(Integer secondUserType) {
		this.secondUserType = secondUserType;
	}
	public Date getWeddingdate() {
		return weddingdate;
	}
	public void setWeddingdate(Date weddingdate) {
		this.weddingdate = weddingdate;
	}
	public Date getModifyDttm() {
		return modifyDttm;
	}
	public void setModifyDttm(Date modifyDttm) {
		this.modifyDttm = modifyDttm;
	}
	public List<Picture> getPictures() {
		return pictures;
	}
	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	
	
}
