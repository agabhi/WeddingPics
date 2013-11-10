package com.weddingpics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class GuestsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guests);
		ImageButton wedIdBtn  = (ImageButton) findViewById(R.id.wedIdBtn);
		wedIdBtn.setOnClickListener(new OnClickListener() {
			 @Override
			  public void onClick(View arg0) {
				 TextView weddingIdInput    =(TextView)findViewById(R.id.weddingIdInput);
				 if (!weddingIdInput.getText().toString().isEmpty()) {
					 Intent intent = new Intent(GuestsActivity.this, AlbumActivity.class);
					 intent.putExtra("weddingId", weddingIdInput.getText().toString());
					 startActivity(intent);
				 } else {
					 Toast.makeText(GuestsActivity.this,"Please entre weddingId." , Toast.LENGTH_LONG).show();
				 }
			 }
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.guests, menu);
		return true;
	}

}
