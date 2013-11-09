package com.weddingpics.rest.entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "user")
public class User implements Serializable{

	private static final long serialVersionUID = 1677112599968027283L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	protected Long userId;
	@Column(name = "password")
	private String password;
	@Column(name = "fullName")
	private String fullName;
	@Column(name = "emailId")
	private String emailId;
	@Column(name = "token")
	private String token;
	@Column(name = "modifyDttm")
	private Date modifyDttm;
	
	
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
	
	



	
}
