package com.weddingpics;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import com.weddingpics.util.SystemUiHider;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class HomeActivity extends Activity {
	
	final Context context = this;
	private Button loginLinkButton;
	private ImageButton guestsButton;
	private ImageButton bridebtn;
	
	private enum ScreenStateEnum
    {
        HOME_SCREEN, WEDDING_ID_SCREEN, BRIDE_SCREEN, LOGIN_SCREEN
    }
	
	private ScreenStateEnum currentScreen = ScreenStateEnum.HOME_SCREEN;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_home);
		
		loginLinkButton = (Button) findViewById(R.id.loginLinkButton);
		 
		// add button listener
		loginLinkButton.setOnClickListener(new OnClickListener() {
		  @Override
		  public void onClick(View arg0) {
			  Intent intent = new Intent(context, LoginActivity.class);
			  startActivity(intent);
		  }
		});
		
		
		guestsButton = (ImageButton) findViewById(R.id.guestsbtn);
		guestsButton.setOnClickListener(new OnClickListener() {
			 @Override
			  public void onClick(View arg0) {
				 Intent intent = new Intent(context, GuestsActivity.class);
				 startActivity(intent);
			 }
		});
		
		bridebtn = (ImageButton) findViewById(R.id.bridebtn);
		bridebtn.setOnClickListener(new OnClickListener() {
			 @Override
			  public void onClick(View arg0) {
				 Intent intent = new Intent(context, BrideActivity.class);
				 startActivity(intent);
			 }
		});
	}
	
	@Override
	public void onBackPressed() {
	    // your code.
	}

}
