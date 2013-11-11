package com.weddingpics;

import android.app.Activity;
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
import com.weddingpics.service.LoginService;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		
		Button submitButton = (Button) findViewById(R.id.loginSubmitButton);
		// if button is clicked, close the custom dialog
		submitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//go back
				try {
					TextView loginemail    =(TextView)findViewById(R.id.loginemail);
					TextView password  =(TextView)findViewById(R.id.password);
					if (!loginemail.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
						HttpRequestObject response = LoginService.getInstance().loginUser(loginemail.getText().toString(),password.getText().toString());
						Gson gson = new Gson();
						ServerResponseObject serverResponseObject = gson.fromJson(response.getResponse(), ServerResponseObject.class);
						if (serverResponseObject.getIsSuccess()) {
							Toast.makeText(LoginActivity.this,"user token number "+serverResponseObject.getUser().getToken(), Toast.LENGTH_LONG).show();	
						} else {
							Toast.makeText(LoginActivity.this,"Some Error occure while user login :  "+serverResponseObject.getErrorMessage(), Toast.LENGTH_LONG).show();
						}
						
					} else if (loginemail.getText().toString().isEmpty()) {
						Toast.makeText(LoginActivity.this,"Please entre user email field. " , Toast.LENGTH_LONG).show();
					}  else if (password.getText().toString().isEmpty()) {
						Toast.makeText(LoginActivity.this,"Please entre user password field." , Toast.LENGTH_LONG).show();
					}
				} catch (Exception e) {
					Log.e("HomeActivity", "Error occured login user.", e);
					Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
				}
			}
		});
	}

}
