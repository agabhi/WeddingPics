package com.weddingpics.model;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6215139618464601353L;
	protected Long userId;
	private String password;
	private String fullName;
	private String emailId;
	private String token;
	private Date modifyDttm;
	private List<Album> albums;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getModifyDttm() {
		return modifyDttm;
	}
	public void setModifyDttm(Date modifyDttm) {
		this.modifyDttm = modifyDttm;
	}
	public List<Album> getAlbums() {
		return albums;
	}
	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}
	
	



	
}
