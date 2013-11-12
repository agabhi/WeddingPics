package com.weddingpics.rest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_album")
public class UserAlbum implements Serializable  {

	private static final long serialVersionUID = -3878512268601980541L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userAlbumId")
	protected Long userAlbumId;
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	@ManyToOne
	@JoinColumn(name = "albumId")
	private Album album;
	@Column(name = "modifyDttm")
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
