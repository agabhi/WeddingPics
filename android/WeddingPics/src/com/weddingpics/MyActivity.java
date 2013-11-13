package com.weddingpics;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.weddingpics.util.SessionManager;

public class MyActivity extends Activity {
	
	 SessionManager session;
	
	  @Override  
	    public boolean onCreateOptionsMenu(Menu menu) {  
	        // Inflate the menu; this adds items to the action bar if it is present. 
		  	session = new SessionManager(getApplicationContext());
		  	if (session.isLoggedIn()) {
		        getMenuInflater().inflate(R.menu.menu_main, menu);//Menu Resource, Menu  
		        return true;
		  	} else {
		  		return false;
		  	}
	    }  
	      
	    @Override  
	    public boolean onOptionsItemSelected(MenuItem item) {  
	    	session = new SessionManager(getApplicationContext()); 
	        switch (item.getItemId()) {  
	            case R.id.menulogout:  
	              session.logoutUser();
	              Toast.makeText(getApplicationContext(),"LOGOUT SUCESFULLY!",Toast.LENGTH_LONG).show();  
	            return true;
	            
	            default:  
	                return super.onOptionsItemSelected(item);  
	        }  
	    }  
}
