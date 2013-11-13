package com.weddingpics.util;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.weddingpics.HomeActivity;
import com.weddingpics.LoginActivity;
 
public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;
     
    // Editor for Shared preferences
    Editor editor;
     
    // Context
    Context _context;
     
    // Shared pref mode
    int PRIVATE_MODE = 0;
     
    // Sharedpref file name
    private static final String PREF_NAME = "WeddingPics";
     
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
     
    // User name (make variable public to access from outside)
    public static final String KEY_TOKEN = "token";
     
    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";
    
    // Email address (make variable public to access from outside)
    public static final String KEY_ACTIVITY	 = "activity";
    
    // Weddding id (make variable public to access from outside)
    public static final String KEY_WEDDING_ID	 = "weddingId";
     
    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
     
    
    
    /**
     * save previous activity which trigger login activity 
     * */
    public void savePreviousActivity(String activity,String weddingId){
   
        // Storing activity in pref
        editor.putString(KEY_ACTIVITY, activity);
         
        // Storing activity in pref
        editor.putString(KEY_WEDDING_ID, weddingId);
        
        // commit changes
        editor.commit();
    }
    
    /**
     * Create login session
     * */
    public void createLoginSession(String token, String email){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
         
        // Storing name in pref
        editor.putString(KEY_TOKEN, token);
         
        // Storing email in pref
        editor.putString(KEY_EMAIL, email);
        
        // Storing activity in pref
        editor.putString(KEY_ACTIVITY, null);
        
        // Storing activity in pref
        editor.putString(KEY_WEDDING_ID, null);
         
        // commit changes
        editor.commit();
    }   
     
    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
             
            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             
            // Staring Login Activity
            _context.startActivity(i);
        }
         
    }
     
     
     
    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_TOKEN, pref.getString(KEY_TOKEN, null));
         
        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        
     // user activity 
        user.put(KEY_ACTIVITY, pref.getString(KEY_ACTIVITY, null));
         
        // return user
        return user;
    }
    
    /**
     * @return login user token 
     */
    public String getUserToken(){
    	return pref.getString(KEY_TOKEN, null);
    }
   
    
    /**
     * @return login user email 
     */
    public String getUserEmail(){
    	return pref.getString(KEY_EMAIL, null);
    }
    
    
    /**
     * @return login user activity 
     */
    public String getUserActivity(){
    	return pref.getString(KEY_ACTIVITY, null);
    }
    
    /**
     * @return login user activity 
     */
    public String getWeddingId(){
    	return pref.getString(KEY_WEDDING_ID, null);
    }
     
    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
         
        // After logout redirect user to Home Activity
        Intent i = new Intent(_context, HomeActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
         
        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         
        // Staring Login Activity
        _context.startActivity(i);
    }
     
    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}