package com.weddingpics;

import java.text.SimpleDateFormat;
import java.util.List;


import android.app.Activity;
import android.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.weddingpics.model.Album;
import com.weddingpics.model.HttpRequestObject;
import com.weddingpics.model.Picture;
import com.weddingpics.model.ServerResponseObject;
import com.weddingpics.service.AlbumService;
import com.weddingpics.service.LoadImageService;

import com.weddingpics.util.ImageHelper;
import com.weddingpics.util.AlbumPictureListAdapter;
import com.weddingpics.util.SessionManager;



public class AlbumActivity extends MyActivity {
	

	final Context context = this;
	Album album = null;


	private static final int CAMERA_REQUEST = 1888;
	private static final int IMAGE_FROM_GALLERY = 1;
	private static final int CAMERA_ACTIVITY = 2;

	TextView firstName;
	TextView secondName;
	TextView wedding_id;
	TextView wedding_date;
	TextView album_desc;
	ImageView cover_image;
	LinearLayout lv1;

	String weddingId;


	SessionManager session;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_album);


		TextView wedds = (TextView) findViewById(R.id.wedds);
		firstName = (TextView) findViewById(R.id.first_user);
		secondName = (TextView) findViewById(R.id.secind_user);
		Typeface font = Typeface.createFromAsset(getAssets(), "BaroqueAntiqueScript.ttf");
		wedds.setTypeface(font);
		wedds.setText("&");

		wedding_id = (TextView) findViewById(R.id.wedding_id);
		wedding_date = (TextView) findViewById(R.id.wedding_date);
		album_desc = (TextView) findViewById(R.id.album_desc);
		cover_image = (ImageView) findViewById(R.id.cover_image);

		lv1 = (LinearLayout) findViewById(R.id.albumListView);


		session = new SessionManager(getApplicationContext());

		Intent intentData = getIntent();

		weddingId = (String) intentData.getSerializableExtra("weddingId");

		refreshAlbum();

		Button take_picture = (Button) findViewById(R.id.take_picture);
		// if button is clicked, open the custom dialog
		take_picture.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setTitle("Choose Image Source");
				builder.setItems(new CharSequence[] { "Gallery", "Camera" }, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = null;
						switch (which) {
						case 0:

							// GET IMAGE FROM THE GALLERY
							intent = new Intent(Intent.ACTION_GET_CONTENT);
							intent.setType("image/*");

							Intent chooser = Intent.createChooser(intent, "Choose a Picture");
							startActivityForResult(chooser, IMAGE_FROM_GALLERY);

							break;

						case 1:
							intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
							startActivityForResult(intent, CAMERA_REQUEST);

							break;

						default:
							break;
						}
					}
				});

				builder.show();

			}
		});

	}

	private void addImagesInLinearLayout(List<Picture> pictures, LinearLayout lv1) throws Exception {
		lv1.removeAllViews();
		if (pictures != null && pictures.size() > 0) {
			for (Picture picture : pictures) {
				LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View view = inflater.inflate(R.layout.image_entry, null);
				TextView userName = (TextView) view.findViewById(R.id.username);
				userName.setText(picture.getUser().getFullName());
				ImageView image = (ImageView) view.findViewById(R.id.image);
				ImageView userImage = (ImageView) view.findViewById(R.id.user_image);
				image.setImageBitmap(LoadImageService.loadBitmap(picture.getUrl()));
				userImage.setImageBitmap(ImageHelper.getRoundedCornerBitmap(LoadImageService.loadBitmap(picture.getUrl()), 12));
				lv1.addView(view);
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Bitmap photo = null;
		if (requestCode == IMAGE_FROM_GALLERY && resultCode == RESULT_OK && null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();
			photo = BitmapFactory.decodeFile(picturePath);
			Intent intent = new Intent(getBaseContext(), CameraActivity.class);
			intent.putExtra("imageBitMap", photo);
			intent.putExtra("albumId", album.getAlbumId());
			startActivityForResult(intent, CAMERA_ACTIVITY);

		}

		if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
			photo = (Bitmap) data.getExtras().get("data");
			Intent intent = new Intent(getBaseContext(), CameraActivity.class);
			intent.putExtra("imageBitMap", photo);
			intent.putExtra("albumId", album.getAlbumId());
			startActivityForResult(intent, CAMERA_ACTIVITY);
		}
		if (requestCode == CAMERA_ACTIVITY) {
			refreshAlbum();
		}

	}

	private void refreshAlbum() {
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
					firstName.setText(album.getFirstUser());

					secondName.setText(album.getSecondUser());

					if (album.getWeddingdate() != null) {
						SimpleDateFormat dateFormat = new SimpleDateFormat(" MMM dd, yyy");
						wedding_date.setText(dateFormat.format(album.getWeddingdate()));
					}
					if (serverResponseObject.getPictures() != null && serverResponseObject.getPictures().size() > 0) {
						final List<Picture> pictures = serverResponseObject.getPictures();
						addImagesInLinearLayout(pictures, lv1);
						// lv1.setAdapter(new AlbumPictureListAdapter(this,
						// pictures));
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
				Toast.makeText(AlbumActivity.this, " Some problem occure getting album data :" + serverResponseObject.getErrorMessage(), Toast.LENGTH_LONG)
						.show();
			}
		} catch (Exception e) {
			Log.e("AlbumActivity", "Error occured getting album.", e);
			Toast.makeText(AlbumActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

}
