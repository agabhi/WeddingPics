package com.weddingpics.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.util.Log;

import com.weddingpics.model.HttpRequestObject;

public class HttpRequestCall {

	
	public static HttpRequestObject executeHttpPost(HttpRequestObject httpRequestObject) throws UnsupportedEncodingException {
		   // Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost(httpRequestObject.getUrl());

	    try {
	        // Adding parameter in url
	    	if (httpRequestObject.getPostParameters() != null && httpRequestObject.getPostParameters().size() > 0) {
	    		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(httpRequestObject.getPostParameters().size());
		        for (Entry<String, String> entry : httpRequestObject.getPostParameters().entrySet()  ) {
		        	nameValuePairs.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));	
		        }
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	    	}

	        // Execute HTTP Post Request
	    	HttpResponse response = httpclient.execute(httppost);
	        String responseBody = EntityUtils.toString(response.getEntity()); 
	        httpRequestObject.setSuccess(true);
	        httpRequestObject.setResponse(responseBody);
	        
	    } catch (ClientProtocolException e) {
	    	httpRequestObject.setSuccess(false);
	    	httpRequestObject.setMessage("HttpRequestCall : Error occured while call url ClientProtocolException : = "+httpRequestObject.getUrl()+" : "+e.getMessage());
			Log.e("HttpRequestCall", "HttpRequestCall : Error occured while call url ClientProtocolException : = "+httpRequestObject.getUrl()+" : ", e);
		} catch (IOException e) {
			httpRequestObject.setSuccess(false);
	    	httpRequestObject.setMessage("HttpRequestCall : Error occured while call url IOException : = "+httpRequestObject.getUrl()+" : "+e.getMessage());
	    	Log.e("HttpRequestCall", "HttpRequestCall : Error occured while call url IOException : = "+httpRequestObject.getUrl()+" : ", e);
		} 
	    
	    return httpRequestObject;
	}
}
