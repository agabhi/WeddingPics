package com.weddingpics;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.RelativeLayout;

public class BrideActivity extends MyActivity {

	final Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_bride);
		RelativeLayout createAlbumButton = (RelativeLayout) findViewById(R.id.createAlbumbutton);
		// if button is clicked, close the custom dialog
		createAlbumButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, WeddingSetupOneActivity.class);
				 startActivity(intent);
			}
		});
		
		RelativeLayout viewAlbumButton = (RelativeLayout) findViewById(R.id.viewAlbumButton);
		// if button is clicked, close the custom dialog
		viewAlbumButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, GuestsActivity.class);
				 startActivity(intent);
			}
		});
	}
}
