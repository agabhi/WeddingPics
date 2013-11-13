package com.weddingpics;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.weddingpics.model.HttpRequestObject;
import com.weddingpics.model.ServerResponseObject;
import com.weddingpics.service.UserService;
import com.weddingpics.util.SessionManager;

public class SignUpActivity extends MyActivity {

	SessionManager session;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_signup);
		session = new SessionManager(getApplicationContext()); 
		Button signupSubmitButton = (Button) findViewById(R.id.signupSubmitButton);
		// if button is clicked, close the custom dialog
		signupSubmitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//go back
				try {
					TextView fullname    =(TextView)findViewById(R.id.fullname);
					TextView signupemail    =(TextView)findViewById(R.id.signupemail);
					TextView retypeemail    =(TextView)findViewById(R.id.retypeemail);
					TextView password  =(TextView)findViewById(R.id.password);
					if (!fullname.getText().toString().isEmpty() && !signupemail.getText().toString().isEmpty() && !retypeemail.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
						if (signupemail.getText().toString().equalsIgnoreCase(retypeemail.getText().toString())) {
							HttpRequestObject response = UserService.getInstance().registerUser(fullname.getText().toString(),signupemail.getText().toString(),password.getText().toString());
							Gson gson = new Gson();
							ServerResponseObject serverResponseObject = gson.fromJson(response.getResponse(), ServerResponseObject.class);
							if (serverResponseObject.getIsSuccess()) {
								Toast.makeText(SignUpActivity.this,"Login sucessful", Toast.LENGTH_LONG).show();
								String activity = session.getUserActivity();
								String weddingId = session.getWeddingId();
								session.createLoginSession(serverResponseObject.getUser().getToken(), serverResponseObject.getUser().getEmailId());
								if (activity.equalsIgnoreCase("HomeActivity")) {
									Intent intent = new Intent(SignUpActivity.this, MyWeddingsActivity.class);
									startActivity(intent);
									finish();
								} else if (activity.equalsIgnoreCase("GuestsActivity")) {
									 Intent intent = new Intent(SignUpActivity.this, AlbumActivity.class);
									 intent.putExtra("weddingId",weddingId);
									 startActivity(intent);
									 finish();
								}
							} else {
								Toast.makeText(SignUpActivity.this," SOME ERROR OCCURE WHILE REGISTER USER :  "+serverResponseObject.getErrorMessage(), Toast.LENGTH_LONG).show();
							}
						} else {
							Toast.makeText(SignUpActivity.this,"EMAIL ID AND RETYPE EMAIL DOES NOT MATCH " , Toast.LENGTH_LONG).show();
						}
					} else if (signupemail.getText().toString().isEmpty()) {
						Toast.makeText(SignUpActivity.this,"PLEASE ENTRE EMAIL ID. " , Toast.LENGTH_LONG).show();
					}else if (retypeemail.getText().toString().isEmpty()) {
						Toast.makeText(SignUpActivity.this,"PLEASE ENTRE RETYPE EMAIL ID." , Toast.LENGTH_LONG).show();
					} else if (password.getText().toString().isEmpty()) {
						Toast.makeText(SignUpActivity.this,"PLEASE ENTRE PASSWORD." , Toast.LENGTH_LONG).show();
					}  else if (fullname.getText().toString().isEmpty()) {
						Toast.makeText(SignUpActivity.this,"PLEASE ENTRE YOUR FULL NAME." , Toast.LENGTH_LONG).show();
					}
					
				} catch (Exception e) {
					Log.e("SignUpActivity", "ERROR OCCURE WHILE REGISTER USER.", e);
					Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
				}
			}
		});
		
	}

}
