package com.weddingpics.service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import android.os.AsyncTask;
import android.util.Log;

import com.weddingpics.HomeActivity;
import com.weddingpics.model.HttpRequestObject;
import com.weddingpics.util.HttpRequestCall;

public class UserService {
	
	private static UserService instance = new UserService();
	
	private final static String GET_USER_ALBUMS_URL =HomeActivity.WP_SERVER_PATH +"uc/getUserAlbums?";
	
	private final static String SIGNUP_URL =HomeActivity.WP_SERVER_PATH +"uc/registerUser?";

	public static UserService getInstance() {
		return instance;
	}
	
	
	public HttpRequestObject userAlbums(String token) throws Exception {
	
		class UserAlbums extends AsyncTask<String, Void, HttpRequestObject> {
			
			@Override
			protected HttpRequestObject doInBackground(String... params) {
				
				String url = params[0];
				String token = params[1];
				
				HttpRequestObject requestObject = new HttpRequestObject();
				requestObject.setUrl(url);
				HashMap<String, String> postParameters = new HashMap<String, String>();
				postParameters.put("token", token);
				requestObject.setPostParameters(postParameters);
				try {
					requestObject = HttpRequestCall.executeHttpPost(requestObject);
				} catch (UnsupportedEncodingException e) {
					requestObject.setSuccess(false);
					requestObject.setMessage("ERROR OCCURE WHILE GETTING USER ALBUMS INFORMATIONS : "+e.getMessage());
					Log.e("UserService", "ERROR OCCURE WHILE GETTING USER ALBUMS INFORMATIONS :", e);
				}
				
				return requestObject;
			}

		}

		HttpRequestObject response = null;
		try {
			response = new UserAlbums().execute(GET_USER_ALBUMS_URL,token).get();
		} catch (Exception e) {
			Log.e("UserService", "ERROR OCCURE WHILE GETTING USER ALBUMS INFORMATIONS : ", e);
			throw new Exception("ERROR OCCURE WHILE GETTING USER ALBUMS INFORMATIONS : ");
		}
		if (response.isSuccess()) {
			return response;
		} else {
			throw new Exception(response.getMessage());
		}
		
	}
	
	
	public HttpRequestObject registerUser(String userName,String userEmail,String password) throws Exception {
		
		class RegisterUser extends AsyncTask<String, Void, HttpRequestObject> {
			
			@Override
			protected HttpRequestObject doInBackground(String... params) {
				
				String url = params[0];
				String userName = params[1];
				String userEmail = params[2];
				String password = params[3];
				
				HttpRequestObject requestObject = new HttpRequestObject();
				requestObject.setUrl(url);
				HashMap<String, String> postParameters = new HashMap<String, String>();
				postParameters.put("userName", userName);
				postParameters.put("userEmail", userEmail);
				postParameters.put("password", password);
				requestObject.setPostParameters(postParameters);
				try {
					requestObject = HttpRequestCall.executeHttpPost(requestObject);
				} catch (UnsupportedEncodingException e) {
					requestObject.setSuccess(false);
					requestObject.setMessage("ERROR OCCURE WHILE REGISTER USER :"+e.getMessage());
					Log.e("UserService", "ERROR OCCURE WHILE REGISTER USER :", e);
				}
				
				return requestObject;
			}

		}

		HttpRequestObject response = null;
		try {
			response = new RegisterUser().execute(SIGNUP_URL,userName,userEmail,password).get();
		} catch (Exception e) {
			Log.e("UserService", "ERROR OCCURE WHILE REGISTER USER :", e);
			throw new Exception("ERROR OCCURE WHILE REGISTER USER :");
		}
		if (response.isSuccess()) {
			return response;
		} else {
			throw new Exception(response.getMessage());
		}
		
	}

}
