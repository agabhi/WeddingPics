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
@Table(name = "picture")
public class Picture implements Serializable {

	private static final long serialVersionUID = -3107749883290944119L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pictureId")
	protected Long pictureId;
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	@ManyToOne
	@JoinColumn(name = "albumId")
	private Album album;
	@Column(name = "pictureTitle")
	private String pictureTitle;
	@Column(name = "url")
	private String url;
	@Column(name = "pictureDate")
	private Date pictureDate;
	@Column(name = "imageType")
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
