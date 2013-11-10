package com.weddingpics;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class WeddingSetupOneActivity extends Activity {
	final Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_wedding_setup_one);
		
		RelativeLayout nextNewUserBtn = (RelativeLayout) findViewById(R.id.nextNewUserBtn);
		// if button is clicked, close the custom dialog
		nextNewUserBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView emailInput    =(TextView)findViewById(R.id.emailInput);
				TextView retypeEmailInput    =(TextView)findViewById(R.id.retypeEmailInput);
				TextView fullNameInput  =(TextView)findViewById(R.id.fullNameInput);
				TextView passwordInput  =(TextView)findViewById(R.id.passwordInput);
				if (!emailInput.getText().toString().isEmpty() && !retypeEmailInput.getText().toString().isEmpty() && !fullNameInput.getText().toString().isEmpty() && !passwordInput.getText().toString().isEmpty()) {
					if (emailInput.getText().toString().equalsIgnoreCase(retypeEmailInput.getText().toString())) {
						 Intent intent = new Intent(context, WeddingSetupTwoActivity.class);
						 intent.putExtra("email", emailInput.getText().toString());
						 intent.putExtra("isNewUser",true);
						 intent.putExtra("fullName", fullNameInput.getText().toString());
						 intent.putExtra("password", passwordInput.getText().toString());
						 startActivity(intent);
					} else {
						Toast.makeText(WeddingSetupOneActivity.this,"Please entre email  & retype email not match. " , Toast.LENGTH_LONG).show();
					}
				} else if (emailInput.getText().toString().isEmpty()) {
					Toast.makeText(WeddingSetupOneActivity.this,"Please entre email field. " , Toast.LENGTH_LONG).show();
				}  else if (retypeEmailInput.getText().toString().isEmpty()) {
					Toast.makeText(WeddingSetupOneActivity.this,"Please entre retype email field." , Toast.LENGTH_LONG).show();
				} else if (fullNameInput.getText().toString().isEmpty()) {
					Toast.makeText(WeddingSetupOneActivity.this,"Please entre full name field. " , Toast.LENGTH_LONG).show();
				}  else if (passwordInput.getText().toString().isEmpty()) {
					Toast.makeText(WeddingSetupOneActivity.this,"Please entre password field." , Toast.LENGTH_LONG).show();
				}
				
			}
		});
		RelativeLayout nextExistingUserBtn = (RelativeLayout) findViewById(R.id.nextExistingUserBtn);
		// if button is clicked, close the custom dialog
		nextExistingUserBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView emailId    =(TextView)findViewById(R.id.emailId);
				TextView password  =(TextView)findViewById(R.id.password);
				if (!emailId.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
						 Intent intent = new Intent(context, WeddingSetupTwoActivity.class);
						 intent.putExtra("isNewUser",false);
						 intent.putExtra("email", emailId.getText().toString());
						 intent.putExtra("password", password.getText().toString());
						 startActivity(intent);
					
				} else if (emailId.getText().toString().isEmpty()) {
					Toast.makeText(WeddingSetupOneActivity.this,"Please entre email field. " , Toast.LENGTH_LONG).show();
				}  else if (password.getText().toString().isEmpty()) {
					Toast.makeText(WeddingSetupOneActivity.this,"Please entre password field." , Toast.LENGTH_LONG).show();
				}
			}
		});
	}

}
