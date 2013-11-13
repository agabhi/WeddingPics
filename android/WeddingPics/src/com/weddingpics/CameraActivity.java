package com.weddingpics;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.weddingpics.model.HttpRequestObject;
import com.weddingpics.model.ServerResponseObject;
import com.weddingpics.service.SaveImageService;
import com.weddingpics.util.ImageTypeEnum;

public class CameraActivity extends MyActivity {
	
	  private final static String DEBUG_TAG = "CameraActivity";
	  private static final int CAMERA_REQUEST = 1888; 
	  private ImageView imageView;
	  private EditText imageDesc;  
	  private Bitmap photo = null;
	  SessionManager session;

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.activity_camera);
	    	session = new SessionManager(getApplicationContext());
	    	
	    	Intent intentData = getIntent();
			final Long albumId = (Long) intentData.getSerializableExtra("albumId");
			final Bitmap imageBitMap = (Bitmap) intentData.getParcelableExtra("imageBitMap");
	        this.imageView = (ImageView)this.findViewById(R.id.imageView1);
	        this.imageDesc = (EditText)this.findViewById(R.id.pictureDescription);
	        
	        Button photoButton = (Button) this.findViewById(R.id.click_photo);
	        photoButton.setOnClickListener(new View.OnClickListener() {

	            @Override
	            public void onClick(View v) {
	                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
	                startActivityForResult(cameraIntent, CAMERA_REQUEST); 
	            }
	        });
	        

	        Button save_photo = (Button) this.findViewById(R.id.save_photo);
	        save_photo.setOnClickListener(new View.OnClickListener() {

	            @Override
	            public void onClick(View v) {
	            	if (albumId != null && photo != null) {
	            		try {
							HttpRequestObject response  = SaveImageService.getInstance().saveImage(photo, albumId, ImageTypeEnum.WEDDING.getValue(), imageDesc.getText().toString(), session.getUserToken());
							Gson gson = new Gson();
							ServerResponseObject serverResponseObject = gson.fromJson(response.getResponse(), ServerResponseObject.class);
							if (serverResponseObject != null && serverResponseObject.getIsSuccess()) {
								Toast.makeText(CameraActivity.this,"Photo save in album sucessfully.", Toast.LENGTH_LONG).show();
							} else {
								Toast.makeText(CameraActivity.this,"Some error occure while saving saving photo in album :"+serverResponseObject.getErrorMessage(), Toast.LENGTH_LONG).show();
							}
						} catch (Exception e) {
							Log.e("CameraActivity", "Error occured saving photo.", e);
							Toast.makeText(CameraActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();
						}
	            	} else if (albumId == null){
	            		Log.i("CameraActivity", "Album id not blank.");
	            		Toast.makeText(CameraActivity.this,"Album id not blank.", Toast.LENGTH_LONG).show();
	            	} else if (photo == null){
	            		Log.i("CameraActivity", "First Take a photo. than call save");
	            		Toast.makeText(CameraActivity.this,"First Take a photo. than call save", Toast.LENGTH_LONG).show();
	            	} 
	            }
	        });
	        
	        Button cover_photo = (Button) this.findViewById(R.id.cover_photo);
	        cover_photo.setOnClickListener(new View.OnClickListener() {

	            @Override
	            public void onClick(View v) {
	            	if (albumId != null && photo != null) {
	            		try {
							HttpRequestObject response  = SaveImageService.getInstance().saveImage(photo, albumId, ImageTypeEnum.COVER .getValue(), imageDesc.getText().toString(), session.getUserToken());
							Gson gson = new Gson();
							ServerResponseObject serverResponseObject = gson.fromJson(response.getResponse(), ServerResponseObject.class);
							if (serverResponseObject != null && serverResponseObject.getIsSuccess()) {
								Toast.makeText(CameraActivity.this,"Sucessfully save album cover.", Toast.LENGTH_LONG).show();
							} else {
								Toast.makeText(CameraActivity.this,"Some error occure while saving saving album cover : "+serverResponseObject.getErrorMessage(), Toast.LENGTH_LONG).show();
							}
						} catch (Exception e) {
							Log.e("CameraActivity", "Error occured saving cover photo.", e);
							Toast.makeText(CameraActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();
						}
	            	} else if (albumId == null){
	            		Log.i("CameraActivity", "Album id not blank.");
	            		Toast.makeText(CameraActivity.this,"Album id not blank.", Toast.LENGTH_LONG).show();
	            	} else if (photo == null){
	            		Log.i("CameraActivity", "First Take a photo. than call save");
	            		Toast.makeText(CameraActivity.this,"First Take a photo. than call save as cover", Toast.LENGTH_LONG).show();
	            	} 
	            }
	        });
	    }

	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
	        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {  
	            photo = (Bitmap) data.getExtras().get("data"); 
	            imageView.setImageBitmap(photo);
	        }  
	    } 
}
