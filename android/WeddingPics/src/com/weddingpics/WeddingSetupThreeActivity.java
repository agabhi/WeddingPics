package com.weddingpics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class WeddingSetupThreeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_wedding_setup_three);
		
		TextView weddingIdText    =(TextView)findViewById(R.id.weddingId);
		Intent intentData = getIntent();
		final String weddingId = (String) intentData.getSerializableExtra("weddingId");
		if (!weddingId.isEmpty()) {
			 weddingIdText.setText(weddingId);
			Toast.makeText(WeddingSetupThreeActivity.this,"Album cerate sucessfully! Weeding ID : "+weddingId, Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(WeddingSetupThreeActivity.this,"Some data missing for album creations." , Toast.LENGTH_LONG).show();
		} 
		
		RelativeLayout goToAlbmBtn = (RelativeLayout) findViewById(R.id.goToAlbmBtn);
		// if button is clicked,than we open albums
		goToAlbmBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//go back
				if (!weddingId.isEmpty()) {
					 Intent intent = new Intent(WeddingSetupThreeActivity.this, AlbumActivity.class);
					 intent.putExtra("weddingId",weddingId);
					 startActivity(intent);
				} else {
					 Toast.makeText(WeddingSetupThreeActivity.this,"WeddingId not blank." , Toast.LENGTH_LONG).show();
				}
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wedding_setup_three, menu);
		return true;
	}

}
