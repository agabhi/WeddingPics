package com.weddingpics;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.weddingpics.model.HttpRequestObject;
import com.weddingpics.model.ServerResponseObject;
import com.weddingpics.service.LoadImageService;
import com.weddingpics.service.SaveImageService;
import com.weddingpics.util.SessionManager;

public class ImageActivity extends MyActivity {
	
SessionManager session;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_image);
		session = new SessionManager(getApplicationContext()); 
		
		Intent intentData = getIntent();

		final Long imageId = (Long) intentData.getSerializableExtra("imageId");
		final String url = (String) intentData.getSerializableExtra("url");
		final String weddingId = (String) intentData.getSerializableExtra("weddingId");
		
		ImageView imageView = (ImageView)findViewById(R.id.imageView);
		try {
			imageView.setImageBitmap(LoadImageService.loadBitmap(url));
		} catch (Exception e1) {
			Log.e("ImageActivity", "ERROR OCCURE WHILE LODING URL : "+url, e1);
			Toast.makeText(ImageActivity.this,"ERROR OCCURE WHILE LODING URL : ", Toast.LENGTH_LONG).show();
		}
		
		
		Button deleteimage = (Button) findViewById(R.id.deleteimage);
		// if button is clicked, close the custom dialog
		deleteimage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//go back
				try {
					if (imageId != null) {
							HttpRequestObject response = SaveImageService.getInstance().deleteImage(imageId);
							Gson gson = new Gson();
							ServerResponseObject serverResponseObject = gson.fromJson(response.getResponse(), ServerResponseObject.class);
							if (serverResponseObject.getIsSuccess()) {
									Toast.makeText(ImageActivity.this,"IMAGE DELETED SUCESSFULLY!", Toast.LENGTH_LONG).show();
									Intent intent = new Intent(ImageActivity.this, AlbumActivity.class);
									intent.putExtra("weddingId",weddingId);
									startActivity(intent);
									finish();
							} else {
								Toast.makeText(ImageActivity.this,serverResponseObject.getErrorMessage(), Toast.LENGTH_LONG).show();
							}
						
					} else if (imageId == null) {
						Toast.makeText(ImageActivity.this,"IMAGE ID NOT NULL. " , Toast.LENGTH_LONG).show();
					}
					
				} catch (Exception e) {
					Log.e("ImageActivity", "ERROR OCCURE WHILE DELEET IMAGE.", e);
					Toast.makeText(ImageActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
				}
			}
		});
		
	}

}



