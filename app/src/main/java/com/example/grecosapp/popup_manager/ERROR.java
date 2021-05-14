package com.example.grecosapp.popup_manager;

import android.content.Context;
import android.widget.Toast;

public class ERROR {

    static public void USER_EXIST(Context context){
        Toast.makeText(context, "This username exist!", Toast.LENGTH_LONG).show();
    }

    public static void USER_OR_PASSWORD_INVALID(Context context) {
        Toast.makeText(context, "Username or Password invalid!", Toast.LENGTH_LONG).show();
    }
}
