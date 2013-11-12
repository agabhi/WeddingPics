package com.weddingpics;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.weddingpics.model.Album;
import com.weddingpics.model.HttpRequestObject;
import com.weddingpics.model.ServerResponseObject;
import com.weddingpics.service.UserService;
import com.weddingpics.util.SessionManager;

public class MyWeddingsActivity extends MyActivity {
	
	  SessionManager session;
	  
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.activity_myweddings);
	    	session = new SessionManager(getApplicationContext());
	    	final ListView userAlbumListView = (ListView) findViewById(R.id.userAlbumListView);
	    	  // Get the reference of ListViewAnimals
	    	try {
				HttpRequestObject response = UserService.getInstance().userAlbums(session.getUserToken());
				Gson gson = new Gson();
				ServerResponseObject serverResponseObject = gson.fromJson(response.getResponse(), ServerResponseObject.class);
				if (serverResponseObject.getIsSuccess()) {
					if (serverResponseObject.getUser() != null && serverResponseObject.getUser().getAlbums() != null && serverResponseObject.getUser().getAlbums().size() > 0) {
						  ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				                    android.R.layout.simple_list_item_1, getWeddings(serverResponseObject.getUser().getAlbums()));
				        
						  userAlbumListView.setAdapter(adapter);						
					}
					
				} else {
					Toast.makeText(MyWeddingsActivity.this," SOME ERROR OCCURE WHILE GETTING USER ALBUMS :"+serverResponseObject.getErrorMessage() , Toast.LENGTH_LONG).show();	
				}
			} catch (Exception e) {
				Log.e("MyWeddingsActivity", "ERROR OCCURE WHILE GETTING USER ALBUMS.", e);
				Toast.makeText(MyWeddingsActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
			}
             
	        Button photoButton = (Button) this.findViewById(R.id.gotoweddings);
	        photoButton.setOnClickListener(new View.OnClickListener() {
	        	@Override
	            public void onClick(View v) {
	                
	            }
	        });
	  }
	  
	  private ArrayList< String> getWeddings(List<Album> albums) {
		    ArrayList< String> list = new ArrayList<String>();
		    if (albums != null && albums.size() > 0) {
		    	for (Album album : albums) {
		    		list.add(album.getWeddingId());		
		    	}
		    }
		    
		    return list;
	    }


}
