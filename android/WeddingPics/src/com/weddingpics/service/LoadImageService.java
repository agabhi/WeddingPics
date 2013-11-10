package com.weddingpics.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

public class LoadImageService {
	
	private static LoadImageService instance = new LoadImageService();
	
	public static LoadImageService getInstance() {
		return instance;
	}
	
	
	/**
	 * This method is user for loadiing image from image url
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static Bitmap  loadBitmap(String url) throws Exception {
		
		class LoadImage extends AsyncTask<String, Void, Bitmap> {
			
			@Override
			protected Bitmap doInBackground(String... params) {
				
				String url = params[0];
				Bitmap bitmap = null;
				try {
		            URL imageURL = new URL(url);
		            HttpURLConnection conn = (HttpURLConnection) imageURL.openConnection();
		            conn.connect();
		            InputStream bitIs = conn.getInputStream();
		            if(bitIs != null){
		            	bitmap = BitmapFactory.decodeStream(bitIs);
		            }else{
		                Log.e("InputStream", "Viene null");
		            }
		        } catch (MalformedURLException e) {
		        	Log.e("LoadImageService", "Error occured while getting album.", e);
				} catch (IOException e) {
		        	Log.e("LoadImageService", "Error occured while getting album.", e);
				}
				
				return bitmap;
			}

		}

		Bitmap bitmap = null;
		try {
			bitmap = new LoadImage().execute(url).get();
		} catch (Exception e) {
			Log.e("LoadImageService", "Error occured while Load Image.", e);
			throw new Exception("Error occured while Load Image.");
		}
		return bitmap;
	}

}
