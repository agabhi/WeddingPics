package com.weddingpics;

import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.weddingpics.model.Album;
import com.weddingpics.model.HttpRequestObject;
import com.weddingpics.model.Picture;
import com.weddingpics.model.ServerResponseObject;
import com.weddingpics.service.AlbumService;
import com.weddingpics.service.LoadImageService;
import com.weddingpics.util.AlbumPictureListAdapter;
import com.weddingpics.util.SessionManager;

public class AlbumActivity extends MyActivity {
	
	final Context context = this;
	Album album = null;
	SessionManager session;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_album);
		session = new SessionManager(getApplicationContext());
		final TextView wedding_id    =(TextView)findViewById(R.id.wedding_id);
		final TextView wedding_date    =(TextView)findViewById(R.id.wedding_date);
		final TextView album_desc    =(TextView)findViewById(R.id.album_desc);
		final ImageView cover_image    =(ImageView)findViewById(R.id.cover_image);
		
		Intent intentData = getIntent();
		final String weddingId = (String) intentData.getSerializableExtra("weddingId");
		try {
			HttpRequestObject response = AlbumService.getInstance().getAlbum(weddingId,session.getUserToken());
			Gson gson = new Gson();
			ServerResponseObject serverResponseObject = gson.fromJson(response.getResponse(), ServerResponseObject.class);
			if (serverResponseObject.getIsSuccess()) {
				if (serverResponseObject.getAlbum() != null) {
					album = serverResponseObject.getAlbum();
					if (album.getWeddingId() != null && !album.getWeddingId().isEmpty()) {
						wedding_id.setText(album.getWeddingId());
					}
					if (album.getWeddingdate() != null) {
						SimpleDateFormat  dateFormat = new SimpleDateFormat(" MMM dd, yyy");
						wedding_date.setText(dateFormat.format(album.getWeddingdate()));
					}
					if (serverResponseObject.getPictures() != null && serverResponseObject.getPictures().size() > 0) {
						final List<Picture> pictures  = serverResponseObject.getPictures();
						final ListView lv1 = (ListView) findViewById(R.id.albumListView);
					    lv1.setAdapter(new AlbumPictureListAdapter(this, pictures));	
					}
					if (album.getCoverImage() != null && !album.getCoverImage().isEmpty()) {
						 Bitmap bitmap = null;
						  try {
								bitmap = LoadImageService.loadBitmap(album.getCoverImage());
							} catch (Exception e) {
								e.printStackTrace();
								Log.e("AlbumActivity", "Error occured while  fetching  loading cover image.", e);
							}
						  if (bitmap != null) {
							  cover_image.setImageBitmap(bitmap);
						  }
					}
					if (album.getDescription() != null && !album.getDescription().isEmpty()) {
						album_desc.setText(album.getDescription());
					}
				}
			} else {
				Toast.makeText(AlbumActivity.this," Some problem occure getting album data :"+serverResponseObject.getErrorMessage() , Toast.LENGTH_LONG).show();	
			}
		} catch (Exception e) {
			Log.e("AlbumActivity", "Error occured getting album.", e);
			Toast.makeText(AlbumActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
		
		Button take_picture = (Button) findViewById(R.id.take_picture);
		// if button is clicked, open the custom dialog
		take_picture.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, CameraActivity.class);
				intent.putExtra("albumId",album.getAlbumId());
				startActivity(intent);
			}
		});
		
	}

}
