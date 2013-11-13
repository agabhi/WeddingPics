package com.weddingpics;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.weddingpics.util.SessionManager;
import com.weddingpics.util.SystemUiHider;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class HomeActivity extends MyActivity {
	
	final Context context = this;
	private Button loginLinkButton;
	private ImageButton guestsButton;
	private ImageButton bridebtn;
	private RelativeLayout loginLayout;
	SessionManager session;
	
	public static String IMAGE_SERVER_PATH = "http://ec2-54-200-230-189.us-west-2.compute.amazonaws.com/images/";
	public static String WP_SERVER_PATH = "http://ec2-54-200-230-189.us-west-2.compute.amazonaws.com:8080/weddingpics/";
	
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
		
		session = new SessionManager(getApplicationContext()); 
		loginLayout = (RelativeLayout) findViewById(R.id.loginLayout);
		if (session.isLoggedIn()) {
			loginLayout.setVisibility(View.GONE);
		}
		
		loginLinkButton = (Button) findViewById(R.id.loginLinkButton);
		 
		// add button listener
		loginLinkButton.setOnClickListener(new OnClickListener() {
		  @Override
		  public void onClick(View arg0) {
			  Intent intent = new Intent(context, LoginActivity.class);
			  session.savePreviousActivity("HomeActivity",null);
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
