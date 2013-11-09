package com.weddingpics.rest.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "album")
public class Album implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6198088723391852323L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "albumId")
	protected Long albumId;
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	@Column(name = "weddingId")
	private String weddingId;
	@Column(name = "firstUser")
	private String firstUser;
	@Column(name = "secondUser")
	private String secondUser;
	@Column(name = "firstUserType")
	private Integer firstUserType;
	@Column(name = "secondUserType")
	private Integer secondUserType;
	@Column(name = "weddingdate")
	private Date weddingdate;
	@Column(name = "description")
	private String description;
	@Column(name = "coverImage")
	private String coverImage;
	@Column(name = "modifyDttm")
	private Date modifyDttm;
	

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "album", targetEntity = Picture.class)
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
