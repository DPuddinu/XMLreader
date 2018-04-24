package com.example.dario.xmlreader.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class RequestHandler {

    private Context context;

    public RequestHandler(Context context) {
        this.context = context;
    }

    public void setResponse(String response,String name) {
        SharedPreferences m = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = m.edit();
        editor.putString(name, response);
        editor.apply();
    }

    public void doRequest(IRequest IRequest, String out){
        IRequest.doRequest(context, string -> setResponse(string,out));
    }
}
