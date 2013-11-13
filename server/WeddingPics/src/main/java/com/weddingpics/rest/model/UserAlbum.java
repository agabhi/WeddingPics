package com.weddingpics.rest.model;

import java.io.Serializable;
import java.util.Date;

public class UserAlbum implements Serializable  {

	
	
	private static final long serialVersionUID = 6876400879452952804L;
	
	protected Long userAlbumId;
	private User user;
	private Album album;
	private Date modifyDttm;
	
	public Long getUserAlbumId() {
		return userAlbumId;
	}
	public void setUserAlbumId(Long userAlbumId) {
		this.userAlbumId = userAlbumId;
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
	public Date getModifyDttm() {
		return modifyDttm;
	}
	public void setModifyDttm(Date modifyDttm) {
		this.modifyDttm = modifyDttm;
	}
	
	

}
