package com.weddingpics.service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import android.os.AsyncTask;
import android.util.Log;

import com.weddingpics.model.HttpRequestObject;
import com.weddingpics.util.HttpRequestCall;



public class LoginService {
	
	private static LoginService instance = new LoginService();
	
	private final static String LOGIN_URL = "http://10.0.2.2:8080/weddingpics/uc/getUser?";

	public static LoginService getInstance() {
		return instance;
	}
	
	
	public HttpRequestObject loginUser(String userName,String password) throws Exception {
	
		class UserLogin extends AsyncTask<String, Void, HttpRequestObject> {
			
			@Override
			protected HttpRequestObject doInBackground(String... params) {
				
				String url = params[0];
				String userName = params[1];
				String password = params[2];
				
				HttpRequestObject requestObject = new HttpRequestObject();
				requestObject.setUrl(url);
				HashMap<String, String> postParameters = new HashMap<String, String>();
				postParameters.put("userEmail", userName);
				postParameters.put("password", password);
				requestObject.setPostParameters(postParameters);
				try {
					requestObject = HttpRequestCall.executeHttpPost(requestObject);
				} catch (UnsupportedEncodingException e) {
					requestObject.setSuccess(false);
					requestObject.setMessage("Error occured while login user."+e.getMessage());
					Log.e("LoginService", "Error occured while login user.", e);
				}
				
				return requestObject;
			}

		}

		HttpRequestObject response = null;
		try {
			response = new UserLogin().execute(LOGIN_URL,userName,password).get();
		} catch (Exception e) {
			Log.e("LoginService", "Error occured while login user.", e);
			throw new Exception("Error occured while login user.");
		}
		if (response.isSuccess()) {
			return response;
		} else {
			throw new Exception(response.getMessage());
		}
		
	}
}
