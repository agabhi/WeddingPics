package com.weddingpics;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.weddingpics.model.HttpRequestObject;
import com.weddingpics.service.AlbumService;

public class WeddingSetupThreeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_wedding_setup_three);
		
		TextView weddingIdText    =(TextView)findViewById(R.id.weddingId);
		Intent intentData = getIntent();
		String email = (String) intentData.getSerializableExtra("email");
		Boolean isNewUser = (Boolean) intentData.getSerializableExtra("isNewUser");
		String fullName = (String) intentData.getSerializableExtra("fullName");
		String password = (String) intentData.getSerializableExtra("password");
		String firstUser = (String) intentData.getSerializableExtra("firstUser");
		Integer firstUserType = (Integer) intentData.getSerializableExtra("firstUserType");
		String secondUser = (String) intentData.getSerializableExtra("secondUser");
		Integer secondUserType = (Integer) intentData.getSerializableExtra("secondUserType");
		String weddingId = (String) intentData.getSerializableExtra("weddingId");
		String weddingdate = (String) intentData.getSerializableExtra("weddingdate");
		
		try {
			if (!email.isEmpty() && !password.isEmpty() && !firstUser.isEmpty()
					&& !secondUser.isEmpty() && !weddingId.isEmpty() && !weddingdate.isEmpty()
					&& isNewUser != null && firstUserType != null && secondUserType != null	) {
				HttpRequestObject reponse  = AlbumService.getInstance().createAlbum(email,fullName,password,firstUser,secondUser,weddingId,weddingdate,firstUserType,secondUserType,isNewUser);
				JSONObject jsonObject = new JSONObject(reponse.getResponse());
				if (jsonObject != null && jsonObject.getString("weddingId") != null) {
						weddingIdText.setText(" "+jsonObject.getString("weddingId"));
						Toast.makeText(WeddingSetupThreeActivity.this,"Album cerate sucessfully! Weeding ID : "+jsonObject.getString("weddingId") , Toast.LENGTH_LONG).show();
				} else {
						Toast.makeText(WeddingSetupThreeActivity.this,"Album cerate some problem try again later! : "+reponse.getResponse(), Toast.LENGTH_LONG).show();	
				}
				
			} else {
				Toast.makeText(WeddingSetupThreeActivity.this,"Some data missing for album creations." , Toast.LENGTH_LONG).show();
			} 
		} catch (Exception e) {
			Log.e("WeddingSetupThreeActivity", "Error occured Album creation.", e);
			Toast.makeText(WeddingSetupThreeActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wedding_setup_three, menu);
		return true;
	}

}
