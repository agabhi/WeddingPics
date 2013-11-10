package com.weddingpics.model;

import java.util.HashMap;

import org.json.JSONObject;


public class HttpRequestObject {
	
	private String url;
	private String response;
	private String message;
	private Boolean success;
	private HashMap<String, String> postParameters;
	private JSONObject jsonObject; 
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public HashMap<String, String> getPostParameters() {
		return postParameters;
	}
	public void setPostParameters(HashMap<String, String> postParameters) {
		this.postParameters = postParameters;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean isSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public JSONObject getJsonObject() {
		return jsonObject;
	}
	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}
		
}
