package com.example.grecosapp.popup_manager;

import android.content.Context;
import android.widget.Toast;

public class INFO {

    static public void USER_ADDED(Context context){
        Toast.makeText(context, "User added correctly!", Toast.LENGTH_LONG).show();
    }

    static public void TEST_MESSAGE(Context context){
        Toast.makeText(context, "Test message detected!", Toast.LENGTH_LONG).show();
    }

    static public void TEST_SPECIFIC_MESSAGE(Context context, String string){
        Toast.makeText(context, string, Toast.LENGTH_LONG).show();
    }


    public static void GET_IP_REQUEST(Context context) {
        Toast.makeText(context, "Loading IP info...", Toast.LENGTH_LONG).show();
    }

    public static void GET_DATA(Context context) {
        Toast.makeText(context, "Loading Data...", Toast.LENGTH_LONG).show();
    }
}
