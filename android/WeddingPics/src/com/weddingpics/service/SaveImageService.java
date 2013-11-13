package com.weddingpics.service;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import com.weddingpics.HomeActivity;
import com.weddingpics.model.HttpRequestObject;
import com.weddingpics.util.HttpRequestCall;

/**
 * @author PC2
 *
 */
public class SaveImageService {
	
private static SaveImageService instance = new SaveImageService();

	private final static String SAVE_IMAGE_URL = HomeActivity.WP_SERVER_PATH+"uc/saveImage?";
	private final static String DELETE_IMAGE_URL = HomeActivity.WP_SERVER_PATH+"uc/deleteImage?";
	
	public static SaveImageService getInstance() {
		return instance;
	}
	
	
	

	/**
	 * Save image on server for specific album
	 * @param albumId
	 * @param imageType
	 * @param imageDesc
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public HttpRequestObject saveImage(Bitmap photo, Long albumId,Integer imageType, String imageDesc,String token) throws Exception {
	
		class SaveImage extends AsyncTask<String, Void, HttpRequestObject> {
			
			@Override
			protected HttpRequestObject doInBackground(String... params) {
				
				String url = params[0];
				String image = params[1];
				String albumId = params[2];
				String imageType = params[3];
				String imageDesc = params[4];
				String token = params[5];
				
				HttpRequestObject requestObject = new HttpRequestObject();
				requestObject.setUrl(url);
				HashMap<String, String> postParameters = new HashMap<String, String>();
				postParameters.put("image", image);
				postParameters.put("albumId", albumId);
				postParameters.put("imageType", imageType);
				postParameters.put("imageDesc", imageDesc);
				postParameters.put("token", token);
				
				requestObject.setPostParameters(postParameters);
				
				try {
					requestObject = HttpRequestCall.executeHttpPost(requestObject);
				} catch (UnsupportedEncodingException e) {
					requestObject.setSuccess(false);
					requestObject.setMessage("Error occured while saving images."+e.getMessage());
					Log.e("SaveImageService", "Error occured while saving images.", e);
				}
				
				return requestObject;
			}

		}

		HttpRequestObject response = null;
		try {
			 ByteArrayOutputStream stream = new ByteArrayOutputStream();
			 photo.compress(Bitmap.CompressFormat.JPEG, 90, stream); //compress to which format you want.
		     byte [] byte_arr = stream.toByteArray();
		     String image_str = Base64.encodeToString(byte_arr, Base64.DEFAULT );
			response = new SaveImage().execute(SAVE_IMAGE_URL,image_str,albumId.toString(),imageType.toString(), imageDesc.toString(), token).get();
		} catch (Exception e) {
			Log.e("SaveImageService", "Error occured while saving images.", e);
			throw new Exception("Error occured while saving images.");
		}
		if (response.isSuccess()) {
			return response;
		} else {
			throw new Exception(response.getMessage());
		}
		
	}
	
	/**
	 * Delete Image on server for specific album
	 * @param imageId
	 * @return
	 * @throws Exception
	 */
	public HttpRequestObject deleteImage(Long imageId) throws Exception {
	
		class DeleteImage extends AsyncTask<String, Void, HttpRequestObject> {
			
			@Override
			protected HttpRequestObject doInBackground(String... params) {
				
				String url = params[0];
				String imageId = params[1];
				
				HttpRequestObject requestObject = new HttpRequestObject();
				requestObject.setUrl(url);
				HashMap<String, String> postParameters = new HashMap<String, String>();
				postParameters.put("imageId", imageId);
				
				requestObject.setPostParameters(postParameters);
				
				try {
					requestObject = HttpRequestCall.executeHttpPost(requestObject);
				} catch (UnsupportedEncodingException e) {
					requestObject.setSuccess(false);
					requestObject.setMessage("ERROR OCCURE WHILE DELETE IMAGE :"+e.getMessage());
					Log.e("SaveImageService : DELETE IMAGE ", "ERROR OCCURE WHILE DELETE IMAGE :", e);
				}
				
				return requestObject;
			}

		}

		HttpRequestObject response = null;
		try {
			 response = new DeleteImage().execute(DELETE_IMAGE_URL,imageId.toString()).get();
		} catch (Exception e) {
			Log.e("SaveImageService : DELETE IMAGE ", "ERROR OCCURE WHILE DELETE IMAGE :", e);
			throw new Exception("ERROR OCCURE WHILE DELETE IMAGE :");
		}
		if (response.isSuccess()) {
			return response;
		} else {
			throw new Exception(response.getMessage());
		}
		
	}

}

