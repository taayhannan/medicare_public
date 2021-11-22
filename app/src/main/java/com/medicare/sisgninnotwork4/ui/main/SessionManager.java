package com.medicare.sisgninnotwork4.ui.main;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    // Shared Preferences
    SharedPreferences sharedPrefer;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context context;

    // Shared Pref mode
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "MySession";

    public static final String USER_NAME = "userName";
    public static final String CATEGORY = "category";


    // Constructor
    public SessionManager(Context context) {
        this.context = context;
        sharedPrefer = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPrefer.edit();
    }
    /**
     * Call this method on/after login to store the details in session
     * */

    public void createLoginSession(String username,String cat) {


        editor.putString(USER_NAME, username);


        editor.putString(CATEGORY, cat);



        // commit changes
        editor.commit();
    }
    public HashMap<String, String> getUserDetails() {

        HashMap<String, String> user = new HashMap<String, String>();
        user.put("userId",sharedPrefer.getString(USER_NAME, null));

        user.put("cat", sharedPrefer.getString(CATEGORY, null));


        return user;
    }
}