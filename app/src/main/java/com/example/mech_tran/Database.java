
package com.example.mech_tran;

import android.content.Context;
import android.content.SharedPreferences;

public class Database {

    private static final String PREFERENCES_NAME = "user_data";

    public static void saveUserData(Context context, String username, String email, String phoneNumber) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", username);
        editor.putString("email", email);
        editor.putString("phoneNumber", phoneNumber);
        editor.apply();
    }

}
