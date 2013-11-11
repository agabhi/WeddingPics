package com.weddingpics.service;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import com.weddingpics.model.HttpRequestObject;
import com.weddingpics.util.HttpRequestCall;

/**
 * @author PC2
 *
 */
public class SaveImageService {
	
private static SaveImageService instance = new SaveImageService();

	private final static String SAVE_IMAGE_URL = "http://10.0.2.2:8080/weddingpics/uc/saveImage?";
	
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
	public HttpRequestObject saveImage(Bitmap photo, Long albumId,Integer imageType, String imageDesc,Integer userId) throws Exception {
	
		class SaveImage extends AsyncTask<String, Void, HttpRequestObject> {
			
			@Override
			protected HttpRequestObject doInBackground(String... params) {
				
				String url = params[0];
				String image = params[1];
				String albumId = params[2];
				String imageType = params[3];
				String imageDesc = params[4];
				String userId = params[5];
				
				HttpRequestObject requestObject = new HttpRequestObject();
				requestObject.setUrl(url);
				HashMap<String, String> postParameters = new HashMap<String, String>();
				postParameters.put("image", image);
				postParameters.put("albumId", albumId);
				postParameters.put("imageType", imageType);
				postParameters.put("imageDesc", imageDesc);
				postParameters.put("userId", userId);
				
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
			response = new SaveImage().execute(SAVE_IMAGE_URL,image_str,albumId.toString(),imageType.toString(), imageDesc.toString(), userId.toString()).get();
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

}
