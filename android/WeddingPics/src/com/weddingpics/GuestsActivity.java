package com.weddingpics;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.weddingpics.util.SessionManager;

public class GuestsActivity extends MyActivity {
	
	private RelativeLayout loginLayout;
	SessionManager session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guests);
		session = new SessionManager(getApplicationContext());
		loginLayout = (RelativeLayout) findViewById(R.id.loginLayout);
		if (session.isLoggedIn()) {
			loginLayout.setVisibility(View.GONE);
		}
		
		ImageButton wedIdBtn  = (ImageButton) findViewById(R.id.wedIdBtn);
		wedIdBtn.setOnClickListener(new OnClickListener() {
			 @Override
			  public void onClick(View arg0) {
				 TextView weddingIdInput    =(TextView)findViewById(R.id.weddingIdInput);
				 if (!weddingIdInput.getText().toString().isEmpty()) {
					 if (session.isLoggedIn()) {
						 Intent intent = new Intent(GuestsActivity.this, AlbumActivity.class);
						 intent.putExtra("weddingId", weddingIdInput.getText().toString());
						 startActivity(intent);
					 } else {
						 Intent intent = new Intent(GuestsActivity.this, LoginActivity.class);
						 intent.putExtra("weddingId", weddingIdInput.getText().toString());
						 session.savePreviousActivity("GuestsActivity",weddingIdInput.getText().toString());
						 startActivity(intent);
					 }
				 } else {
					 Toast.makeText(GuestsActivity.this,"PLEASE ENTRE WEDDDING ID" , Toast.LENGTH_LONG).show();
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
