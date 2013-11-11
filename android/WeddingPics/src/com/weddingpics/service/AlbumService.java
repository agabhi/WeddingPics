package com.weddingpics.service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import android.os.AsyncTask;
import android.util.Log;

import com.weddingpics.HomeActivity;
import com.weddingpics.model.HttpRequestObject;
import com.weddingpics.util.HttpRequestCall;

public class AlbumService {

private static AlbumService instance = new AlbumService();
	
	private final static String CREATE_ALBUM_URL = HomeActivity.WP_SERVER_PATH + "uc/createAlbum?";
	private final static String GET_ALBUM_URL = HomeActivity.WP_SERVER_PATH + "uc/getAlbum?";

	public static AlbumService getInstance() {
		return instance;
	}
	
	
	/**
	 * This method use for create album. if user not exist than also create user 
	 * @param email
	 * @param fullName
	 * @param password
	 * @param firstUser
	 * @param secondUser
	 * @param weddingId
	 * @param weddingdate
	 * @param firstUserType
	 * @param secondUserType
	 * @param isNewUser
	 * @return
	 * @throws Exception
	 */
	public HttpRequestObject createAlbum(String email,String fullName,String password,String firstUser,String secondUser,String weddingId,String weddingdate,Integer firstUserType,Integer secondUserType,Boolean isNewUser) throws Exception {
	
		class CreateAlbum extends AsyncTask<String, Void, HttpRequestObject> {
			
			@Override
			protected HttpRequestObject doInBackground(String... params) {
				
				String url = params[0];
				String email = params[1];
				String fullName = params[2];
				String password = params[3];
				String firstUser = params[4];
				String secondUser = params[5];
				String weddingId = params[6];
				String weddingdate = params[7];
				String firstUserType = params[8];
				String secondUserType = params[9];
				String isNewUser = params[10];
				
				HttpRequestObject requestObject = new HttpRequestObject();
				requestObject.setUrl(url);
				HashMap<String, String> postParameters = new HashMap<String, String>();
				postParameters.put("email", email);
				postParameters.put("fullName", fullName);
				postParameters.put("password", password);
				postParameters.put("firstUser", firstUser);
				postParameters.put("secondUser", secondUser);
				postParameters.put("weddingId", weddingId);
				postParameters.put("weddingdate", weddingdate);
				postParameters.put("firstUserType", firstUserType);
				postParameters.put("secondUserType", secondUserType);
				postParameters.put("isNewUser", isNewUser);
				requestObject.setPostParameters(postParameters);
				try {
					requestObject = HttpRequestCall.executeHttpPost(requestObject);
				} catch (UnsupportedEncodingException e) {
					requestObject.setSuccess(false);
					requestObject.setMessage("Error occured while Album creation."+e.getMessage());
					Log.e("AlbumService", "Error occured while Album creation.", e);
				}
				
				return requestObject;
			}

		}

		HttpRequestObject response = null;
		try {
			response = new CreateAlbum().execute(CREATE_ALBUM_URL,email,fullName,password,firstUser,secondUser,weddingId,weddingdate,firstUserType.toString(),secondUserType.toString(),isNewUser.toString()).get();
		} catch (Exception e) {
			Log.e("AlbumService", "Error occured while Album creation.", e);
			throw new Exception("Error occured while Album creation.");
		}
		if (response.isSuccess()) {
			return response;
		} else {
			throw new Exception(response.getMessage());
		}
		
	}
	
	/** 
	 * This method is used for getting wedding album using wedding id. 
	 * @param weddingId
	 * @return
	 * @throws Exception
	 */
	public HttpRequestObject getAlbum(String weddingId) throws Exception {
		
		class GetAlbum extends AsyncTask<String, Void, HttpRequestObject> {
			
			@Override
			protected HttpRequestObject doInBackground(String... params) {
				
				String url = params[0];
				String weddingId = params[1];
				
				
				HttpRequestObject requestObject = new HttpRequestObject();
				requestObject.setUrl(url);
				HashMap<String, String> postParameters = new HashMap<String, String>();
				postParameters.put("weddingId", weddingId);
				requestObject.setPostParameters(postParameters);
				try {
					requestObject = HttpRequestCall.executeHttpPost(requestObject);
				} catch (UnsupportedEncodingException e) {
					requestObject.setSuccess(false);
					requestObject.setMessage("Error occured while getting album."+e.getMessage());
					Log.e("AlbumService", "Error occured while getting album.", e);
				}
				
				return requestObject;
			}

		}

		HttpRequestObject response = null;
		try {
			response = new GetAlbum().execute(GET_ALBUM_URL,weddingId).get();
		} catch (Exception e) {
			Log.e("AlbumService", "Error occured while getting album.", e);
			throw new Exception("Error occured while getting album.");
		}
		if (response.isSuccess()) {
			return response;
		} else {
			throw new Exception(response.getMessage());
		}
		
	}
}
